package com.c4pn91.opchallenge.data.remote.api

import com.c4pn91.opchallenge.data.remote.model.MoviesResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApiClient {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MoviesResponseDTO>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): Response<MoviesResponseDTO>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): Response<MoviesResponseDTO>
}