package com.example.myst.domain

import com.example.myst.data.Results

interface MoviesRepository {
    suspend fun getAllMovies():Results
}