package com.app.pagingexample.service

import com.app.pagingexample.model.Team
import io.reactivex.Observable
import javax.inject.Inject

class WebServiceImpl @Inject constructor(private var service: RetrofitService) : WebService {

    override fun fetchAllTeams(page: Int): Observable<List<Team>> {
        return service.fetchTeams(page)
    }
}