package com.c4pn91.opchallenge.data.datasource.remote

import com.c4pn91.opchallenge.data.remote.api.PersonApiClient
import com.c4pn91.opchallenge.data.remote.model.PeopleResponseDTO
import com.c4pn91.opchallenge.data.remote.model.ResultsDTO
import retrofit2.Response
import javax.inject.Inject
import kotlin.jvm.Throws

class RemotePersonDataSource @Inject constructor(private val api: PersonApiClient) {

    @Throws(Exception::class)
    suspend fun getPersonFromApi(): ResultsDTO? {
        return try {
            val response: Response<PeopleResponseDTO> = api.getPopularPeople()
            val listResponse: List<ResultsDTO> = response.body()?.results ?: emptyList()

            if (response.isSuccessful && listResponse.isNotEmpty()) {
                listResponse.maxByOrNull { it.popularity ?: Double.MIN_VALUE }
            } else {
                throw Exception(response.message())
            }
        } catch (error: Exception) {
            throw error
        }
    }
}