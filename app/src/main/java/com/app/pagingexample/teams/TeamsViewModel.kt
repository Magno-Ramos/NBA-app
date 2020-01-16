package com.app.pagingexample.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.app.pagingexample.model.NetworkState
import com.app.pagingexample.model.Team
import com.app.pagingexample.service.WebService
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class TeamsViewModel @Inject constructor(webService: WebService) : ViewModel() {

    private val networkState = MutableLiveData<NetworkState>()
    private val compositeDisposable = CompositeDisposable()
    private val dataSourceFactory =
        TeamsDataSourceFactory(webService, compositeDisposable, networkState)

    fun getPagedTeams(): LiveData<PagedList<Team>> {
        return LivePagedListBuilder<Int, Team>(dataSourceFactory, PAGE_SIZE).build()
    }

    fun networkStateLiveData(): LiveData<NetworkState> = networkState

    fun invalidateDataSource() = dataSourceFactory.sourceLiveData.value?.invalidate()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}