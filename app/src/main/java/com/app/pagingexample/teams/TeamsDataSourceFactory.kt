package com.app.pagingexample.teams

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.app.pagingexample.model.NetworkState
import com.app.pagingexample.model.Team
import com.app.pagingexample.service.WebService
import io.reactivex.disposables.CompositeDisposable

class TeamsDataSourceFactory(
    private val webService: WebService,
    private val compositeDisposable: CompositeDisposable,
    private val networkState: MutableLiveData<NetworkState>
) : DataSource.Factory<Int, Team>() {

    val sourceLiveData = MutableLiveData<TeamsDataSource>()
    var latestSource: TeamsDataSource? = null

    override fun create(): DataSource<Int, Team> {
        latestSource = TeamsDataSource(webService, compositeDisposable, networkState)
        sourceLiveData.postValue(latestSource)
        return latestSource!!
    }
}