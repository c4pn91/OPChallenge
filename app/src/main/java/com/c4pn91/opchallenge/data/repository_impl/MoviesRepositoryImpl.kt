package com.c4pn91.opchallenge.data.repository_impl

import com.c4pn91.opchallenge.data.datasource.local.LocalMoviesDataSource
import com.c4pn91.opchallenge.data.datasource.remote.RemoteMoviesDataSource
import com.c4pn91.opchallenge.data.remote.model.MoviesDTO
import com.c4pn91.opchallenge.data.remote.model.toMovie
import com.c4pn91.opchallenge.domain.model.Movie
import com.c4pn91.opchallenge.domain.model.Person
import com.c4pn91.opchallenge.domain.repository_inte.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteMovieDataSource: RemoteMoviesDataSource,
    private val localMovieDataSource: LocalMoviesDataSource
) : MoviesRepository {

    override suspend fun getPopularMovies() : Result<List<Movie>> {
        return try {
            val resultMoviesFromApi : List<MoviesDTO>? = remoteMovieDataSource.getPopularMoviesFromApi()
            if (resultMoviesFromApi != null) {
                localMovieDataSource.savePopularMoviesToDb(resultMoviesFromApi)
                val movies = resultMoviesFromApi.map { it.toMovie() }
                Result.success(movies)
            } else {
                return getPopularMoviesFromDb()
            }
        } catch (error: Exception) {
            val result = getPopularMoviesFromDb()
            if(result.isSuccess) return result
            Result.failure(Throwable(error.message ?: "Películas no encontradas"))
        }
    }

    private suspend fun getPopularMoviesFromDb() : Result<List<Movie>> {
        return localMovieDataSource.getPopularMoviesFromDb()
    }

    override suspend fun getTopRatedMovies() : Result<List<Movie>> {
        return try {
            val resultMoviesFromApi : List<MoviesDTO>? = remoteMovieDataSource.getTopRatedMoviesFromApi()
            if (resultMoviesFromApi != null) {
                localMovieDataSource.saveTopRatedMoviesToDb(resultMoviesFromApi)
                val movies = resultMoviesFromApi.map { it.toMovie() }
                Result.success(movies)
            } else {
                return getTopRatedMoviesFromDb()
            }
        } catch (error: Exception) {
            val result = getTopRatedMoviesFromDb()
            if(result.isSuccess) return result
            Result.failure(Throwable(error.message ?: "Películas no encontradas"))
        }
    }

    private suspend fun getTopRatedMoviesFromDb() : Result<List<Movie>> {
        return localMovieDataSource.getTopRatedMoviesFromDb()
    }

    override suspend fun getUpcomingMovies() : Result<List<Movie>> {
        return try {
            val resultMoviesFromApi : List<MoviesDTO>? = remoteMovieDataSource.getUpcomingMoviesFromApi()
            if (resultMoviesFromApi != null) {
                localMovieDataSource.saveUpcomingMoviesToDb(resultMoviesFromApi)
                val movies = resultMoviesFromApi.map { it.toMovie() }
                Result.success(movies)
            } else {
                return getUpcomingMoviesFromDb()
            }
        } catch (error: Exception) {
            val result = getUpcomingMoviesFromDb()
            if(result.isSuccess) return result
            Result.failure(Throwable(error.message ?: "Películas no encontradas"))
        }
    }

    private suspend fun getUpcomingMoviesFromDb() : Result<List<Movie>> {
        return localMovieDataSource.getUpcomingMoviesFromDb()
    }

}