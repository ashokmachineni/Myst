package com.example.myst.domain

import android.util.Log
import com.example.myst.data.MovieItem
import com.example.myst.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    operator fun invoke(): Flow<Resource<List<MovieItem>>> = flow {
        Log.d("ashok",moviesRepository.getAllMovies().toString())
        try {
            emit(Resource.Loading())


            val moviesData = moviesRepository.getAllMovies().results
            Log.d("ashok",moviesData.toString())
            emit(Resource.Success(moviesData))

        }catch (e: Exception){
            Log.d("ashok",e.message.toString())
            emit(Resource.Error(e.message.toString()))
        }
    }
}