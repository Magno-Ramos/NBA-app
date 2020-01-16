package com.app.pagingexample.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("teamId")
    val id: Int,
    val abbreviation: String,
    val teamName: String,
    val simpleName: String,
    val location: String,
    val image: String
)