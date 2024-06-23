package com.c4pn91.opchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c4pn91.opchallenge.domain.model.Movie
import com.c4pn91.opchallenge.domain.usecase.GetPopularMoviesUseCase
import com.c4pn91.opchallenge.domain.usecase.GetTopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class TopRatedMoviesState {
    data object Init                       : TopRatedMoviesState()
    data object Loading                    : TopRatedMoviesState()
    data object Success                    : TopRatedMoviesState()
    data class  Failure(val error: String) : TopRatedMoviesState()
}

@HiltViewModel
class TopRatedMoviesViewModel @Inject constructor(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<TopRatedMoviesState>(TopRatedMoviesState.Init)
    val state: StateFlow<TopRatedMoviesState> = _state

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    fun getTopRatedMovies() {
        _state.value = TopRatedMoviesState.Loading

        viewModelScope.launch {
            val result = getTopRatedMoviesUseCase.invoke()

            result.onSuccess { movies ->
                _movies.value = movies
                _state.value = TopRatedMoviesState.Success
            }.onFailure { error ->
                _state.value = TopRatedMoviesState.Failure(error.message ?: "Unknown error")
            }
        }
    }
}