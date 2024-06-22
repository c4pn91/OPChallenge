package com.c4pn91.opchallenge.data.datasource.local

import com.c4pn91.opchallenge.data.local.dao.PopularMoviesDao
import com.c4pn91.opchallenge.data.local.entitie.PopularMoviesEntity
import com.c4pn91.opchallenge.data.local.entitie.toMovie
import com.c4pn91.opchallenge.data.remote.model.MoviesDTO
import com.c4pn91.opchallenge.data.remote.model.toPopularMovieEntity
import com.c4pn91.opchallenge.domain.model.Movie
import javax.inject.Inject

class LocalMoviesDataSource @Inject constructor(private val popularMoviesDao: PopularMoviesDao) {

    @Throws(Exception::class)
    suspend fun savePopularMoviesToDb(popularMovie: List<MoviesDTO>) {
        try {
            val popularMovieEntity = popularMovie.map { it.toPopularMovieEntity() }

            popularMoviesDao.deleteAllPopularMovies()
            popularMoviesDao.insertAllPopularMovies(popularMovieEntity)
        } catch (e: Exception) {
            throw e
        }
    }

    @Throws(Exception::class)
    suspend fun getPopularMoviesFromDb() : Result<List<Movie>> {
        return try {
            val result: List<PopularMoviesEntity> = popularMoviesDao.getAllPopularMovies()
            Result.success(result.map { it.toMovie() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}