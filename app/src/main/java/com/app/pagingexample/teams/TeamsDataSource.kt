package com.app.pagingexample.teams

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.app.pagingexample.model.NetworkState
import com.app.pagingexample.model.Team
import com.app.pagingexample.service.WebService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TeamsDataSource(
    private val webService: WebService,
    private val compositeDisposable: CompositeDisposable,
    private val networkState: MutableLiveData<NetworkState>
) : PageKeyedDataSource<Int, Team>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Team>
    ) {
        fetchDataFromService(0) { teams ->
            callback.onResult(teams, null, 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Team>) {
        val page = params.key
        fetchDataFromService(page) { teams -> callback.onResult(teams, page + 1) }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Team>) {

    }

    private fun fetchDataFromService(page: Int, callback: (List<Team>) -> Unit) {
        val disposable = webService.fetchAllTeams(page)
            .observeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { networkState.postValue(NetworkState.loading()) }
            .subscribe(
                { teams ->
                    networkState.postValue(NetworkState.success())
                    callback.invoke(teams)
                },
                { err ->
                    networkState.postValue(
                        NetworkState.error(
                            err.message ?: "Request Failed"
                        )
                    )
                }
            )

        compositeDisposable.add(disposable)
    }
}