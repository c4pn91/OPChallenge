package com.c4pn91.opchallenge.data.repository_impl

import com.c4pn91.opchallenge.data.datasource.local.LocalPersonDataSource
import com.c4pn91.opchallenge.data.datasource.remote.RemotePersonDataSource
import com.c4pn91.opchallenge.data.remote.model.ResultsDTO
import com.c4pn91.opchallenge.data.remote.model.toPerson
import com.c4pn91.opchallenge.domain.model.Person
import com.c4pn91.opchallenge.domain.repository_inte.PersonRepository
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val remotePersonDataSource: RemotePersonDataSource,
    private val localPersonDataSource: LocalPersonDataSource
) : PersonRepository {

    override suspend fun getPerson() : Result<Person> {
        return try {
            val resultPersonFromApi : ResultsDTO? = remotePersonDataSource.getPersonFromApi()
            if (resultPersonFromApi != null) {
                localPersonDataSource.savePersonToDb(resultPersonFromApi)
                val personFromApi = resultPersonFromApi.toPerson()
                Result.success(personFromApi)
            } else {
                return getPersonFromDb()
            }
        } catch (e: Exception) {
            val result = getPersonFromDb()
            if(result.isSuccess) return result
            else Result.failure(Exception("Person not found, please check your connection and try again "))
        }
    }

    private suspend fun getPersonFromDb() : Result<Person> {
        return localPersonDataSource.getPersonFromDb()
    }

}