package com.example.myst.domain

import com.example.myst.data.MovieApi
import com.example.myst.data.Results
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieApi: MovieApi): MoviesRepository {
    override suspend fun getAllMovies(): Results {
        return movieApi.getAllMovies("7f945f914277633f019081c00561f647")
    }
}