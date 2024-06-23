package com.c4pn91.opchallenge.domain.usecase

import com.c4pn91.opchallenge.data.repository_impl.MoviesRepositoryImpl
import com.c4pn91.opchallenge.domain.model.Movie
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepositoryImpl
) {
    suspend operator fun invoke() : Result<List<Movie>> {
        return moviesRepository.getTopRatedMovies()
    }
}