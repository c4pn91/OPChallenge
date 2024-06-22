package com.c4pn91.opchallenge.data.remote.api

import com.c4pn91.opchallenge.data.remote.model.PeopleResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface PersonApiClient {

    @GET("person/popular")
    suspend fun getPopularPeople(): Response<PeopleResponseDTO>

}