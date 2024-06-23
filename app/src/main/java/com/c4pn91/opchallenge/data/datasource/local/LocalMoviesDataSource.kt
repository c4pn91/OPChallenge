package com.c4pn91.opchallenge.data.datasource.local

import com.c4pn91.opchallenge.data.local.dao.PopularMoviesDao
import com.c4pn91.opchallenge.data.local.dao.TopRatedMoviesDao
import com.c4pn91.opchallenge.data.local.dao.UpcomingMoviesDao
import com.c4pn91.opchallenge.data.local.entitie.PopularMoviesEntity
import com.c4pn91.opchallenge.data.local.entitie.TopRatedMoviesEntity
import com.c4pn91.opchallenge.data.local.entitie.UpcomingMoviesEntity
import com.c4pn91.opchallenge.data.local.entitie.toMovie
import com.c4pn91.opchallenge.data.remote.model.MoviesDTO
import com.c4pn91.opchallenge.data.remote.model.toPopularMovieEntity
import com.c4pn91.opchallenge.data.remote.model.toTopRatedMovieEntity
import com.c4pn91.opchallenge.data.remote.model.toUpcomingMovieEntity
import com.c4pn91.opchallenge.domain.model.Movie
import javax.inject.Inject

class LocalMoviesDataSource @Inject constructor(
    private val popularMoviesDao: PopularMoviesDao,
    private val topRatedMoviesDao: TopRatedMoviesDao,
    private val upcomingMoviesDao: UpcomingMoviesDao
) {

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

    @Throws(Exception::class)
    suspend fun saveTopRatedMoviesToDb(topRatedMovie: List<MoviesDTO>) {
        try {
            val topRatedMovieEntity = topRatedMovie.map { it.toTopRatedMovieEntity() }

            topRatedMoviesDao.deleteAllTopRatedMovies()
            topRatedMoviesDao.insertAllTopRatedMovies(topRatedMovieEntity)
        } catch (e: Exception) {
            throw e
        }
    }

    @Throws(Exception::class)
    suspend fun getTopRatedMoviesFromDb() : Result<List<Movie>> {
        return try {
            val result: List<TopRatedMoviesEntity> = topRatedMoviesDao.getAllTopRatedMovies()
            Result.success(result.map { it.toMovie() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    @Throws(Exception::class)
    suspend fun saveUpcomingMoviesToDb(upcomingMovie: List<MoviesDTO>) {
        try {
            val upcomingMovieEntity = upcomingMovie.map { it.toUpcomingMovieEntity() }

            upcomingMoviesDao.deleteAllUpcomingMovies()
            upcomingMoviesDao.insertAllUpcomingMovies(upcomingMovieEntity)
        } catch (e: Exception) {
            throw e
        }
    }

    @Throws(Exception::class)
    suspend fun getUpcomingMoviesFromDb() : Result<List<Movie>> {
        return try {
            val result: List<UpcomingMoviesEntity> = upcomingMoviesDao.getAllUpcomingMovies()
            Result.success(result.map { it.toMovie() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}