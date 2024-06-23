package com.c4pn91.opchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c4pn91.opchallenge.domain.model.Movie
import com.c4pn91.opchallenge.domain.usecase.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UpcomingMoviesState {
    data object Init                       : UpcomingMoviesState()
    data object Loading                    : UpcomingMoviesState()
    data object Success                    : UpcomingMoviesState()
    data class  Failure(val error: String) : UpcomingMoviesState()
}

@HiltViewModel
class UpcomingMoviesViewModel @Inject constructor(
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UpcomingMoviesState>(UpcomingMoviesState.Init)
    val state: StateFlow<UpcomingMoviesState> = _state

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    fun getUpcomingMovies() {
        _state.value = UpcomingMoviesState.Loading

        viewModelScope.launch {
            val result = getUpcomingMoviesUseCase.invoke()

            result.onSuccess { movies ->
                _movies.value = movies
                _state.value = UpcomingMoviesState.Success
            }.onFailure { error ->
                _state.value = UpcomingMoviesState.Failure(error.message ?: "Unknown error")
            }
        }
    }
}