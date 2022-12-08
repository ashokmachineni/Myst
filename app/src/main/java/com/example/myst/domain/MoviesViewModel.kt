package com.example.myst.domain

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myst.data.MovieItem
import com.example.myst.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase): ViewModel() {
    private val _movieVMData = mutableStateOf(MoviesDataState())
    val movieVmData: State<MoviesDataState> = _movieVMData

    init {
        getMovieData()
    }

    fun getMovieData(){
        moviesUseCase().onEach { result ->
            when(result){
                is Resource.Loading -> {
                    _movieVMData.value = MoviesDataState(isLoading = true)
                }
                is Resource.Success -> {
                    _movieVMData.value = MoviesDataState(isSuccess = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _movieVMData.value = MoviesDataState(isLoading = false, isError = "no connection")
                }
            }
        }.launchIn(viewModelScope)

    }
}

data class MoviesDataState(
    val isLoading: Boolean = false,
    val isError: String = "",
    val isSuccess: List<MovieItem> = emptyList()
)