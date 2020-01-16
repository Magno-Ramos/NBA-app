package com.app.pagingexample.service

import com.app.pagingexample.model.Team
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("teams")
    fun fetchTeams(@Query("page") page: Int): Observable<List<Team>>

    companion object {
        const val BASE_URL = "https://api-nba.herokuapp.com/nba/"
    }
}