package com.app.pagingexample.service

import com.app.pagingexample.model.Team
import io.reactivex.Observable

interface WebService {

    fun fetchAllTeams(page: Int): Observable<List<Team>>
}