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
            Result.failure(Throwable(error.message ?: "Movies not found"))
        }
    }

    private suspend fun getPopularMoviesFromDb() : Result<List<Movie>> {
        return localMovieDataSource.getPopularMoviesFromDb()
    }

    /*suspend fun getTopRatedMovies() : Result<List<Movie>> {
        try {
            val resultMoviesFromApi : List<MoviesDTO> = moviesService.getMoviesFromApi()
            if (resultMoviesFromApi.isNotEmpty()) {
                moviesService.insertAllMovies(resultMoviesFromApi.map { it.toMovieEntity() })
                val moviesApi = resultMoviesFromApi.map { it.toMovie() }
                Result.success(moviesApi)
            } else {
                getMovieFromDB("No se pudo obtener la lista de películas, intente de nuevo")
            }
        } catch (error: Exception) {
            getMovieFromDB(error.message ?: "Ocurrió un error desconocido")
        }
    }

    suspend fun getUpcomingMovies() : Result<List<Movie>> {
        try {
            val resultMoviesFromApi : List<MoviesDTO> = moviesService.getMoviesFromApi()
            if (resultMoviesFromApi.isNotEmpty()) {
                moviesService.insertAllMovies(resultMoviesFromApi.map { it.toMovieEntity() })
                val moviesApi = resultMoviesFromApi.map { it.toMovie() }
                Result.success(moviesApi)
            } else {
                getMovieFromDB("No se pudo obtener la lista de películas, intente de nuevo")
            }
        } catch (error: Exception) {
            getMovieFromDB(error.message ?: "Ocurrió un error desconocido")
        }
    }*/

}