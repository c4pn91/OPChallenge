package com.c4pn91.opchallenge.data.datasource.remote

import com.c4pn91.opchallenge.data.remote.api.MoviesApiClient
import com.c4pn91.opchallenge.data.remote.model.MoviesDTO
import com.c4pn91.opchallenge.data.remote.model.MoviesResponseDTO
import retrofit2.Response
import javax.inject.Inject
import kotlin.jvm.Throws

class RemoteMoviesDataSource @Inject constructor(private val apiClient: MoviesApiClient) {

    @Throws(Exception::class)
    suspend fun getPopularMoviesFromApi(): List<MoviesDTO>? {
        return try {
            val response: Response<MoviesResponseDTO> = apiClient.getPopularMovies()
            val listResponse: List<MoviesDTO> = response.body()?.movies ?: emptyList()

            if (response.isSuccessful && listResponse.isNotEmpty()) {
                listResponse
            } else {
                throw Exception(response.message())
            }
        } catch (error: Exception) {
            throw error
        }
    }

    @Throws(Exception::class)
    suspend fun getTopRatedMoviesFromApi(): List<MoviesDTO>? {
        return try {
            val response: Response<MoviesResponseDTO> = apiClient.getTopRatedMovies()
            val listResponse: List<MoviesDTO> = response.body()?.movies ?: emptyList()

            if (response.isSuccessful && listResponse.isNotEmpty()) {
                listResponse
            } else {
                throw Exception(response.message())
            }
        } catch (error: Exception) {
            throw error
        }
    }

    @Throws(Exception::class)
    suspend fun getUpcomingMoviesFromApi(): List<MoviesDTO>? {
        return try {
            val response: Response<MoviesResponseDTO> = apiClient.getUpcomingMovies()
            val listResponse: List<MoviesDTO> = response.body()?.movies ?: emptyList()

            if (response.isSuccessful && listResponse.isNotEmpty()) {
                listResponse
            } else {
                throw Exception(response.message())
            }
        } catch (error: Exception) {
            throw error
        }
    }
}