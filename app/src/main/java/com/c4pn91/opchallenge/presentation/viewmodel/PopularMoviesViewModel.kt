package com.c4pn91.opchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c4pn91.opchallenge.domain.model.Movie
import com.c4pn91.opchallenge.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class PopularMoviesState {
    data object Init                       : PopularMoviesState()
    data object Loading                    : PopularMoviesState()
    data object Success                    : PopularMoviesState()
    data class  Failure(val error: String) : PopularMoviesState()
}

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<PopularMoviesState>(PopularMoviesState.Init)
    val state: StateFlow<PopularMoviesState> = _state

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    fun getPopularMovies() {
        _state.value = PopularMoviesState.Loading

        viewModelScope.launch {
            val result = getPopularMoviesUseCase.invoke()

            result.onSuccess { movies ->
                _movies.value = movies
                _state.value = PopularMoviesState.Success
            }.onFailure { error ->
                _state.value = PopularMoviesState.Failure(error.message ?: "Unknown error")
            }
        }
    }
}