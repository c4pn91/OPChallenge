package com.c4pn91.opchallenge.domain.repository_inte

import com.c4pn91.opchallenge.domain.model.Movie

interface MoviesRepository {
    suspend fun getPopularMovies(): Result<List<Movie>>
}