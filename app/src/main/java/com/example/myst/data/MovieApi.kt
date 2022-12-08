package com.example.myst.data

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("popular")
    suspend fun getAllMovies(@Query("api_key")api_key: String): Results
}