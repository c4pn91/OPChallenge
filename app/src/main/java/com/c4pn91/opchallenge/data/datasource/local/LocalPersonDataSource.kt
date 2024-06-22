package com.c4pn91.opchallenge.data.datasource.local

import com.c4pn91.opchallenge.data.local.dao.PersonDao
import com.c4pn91.opchallenge.data.local.relation.PersonWithKnownFor
import com.c4pn91.opchallenge.data.local.relation.toPerson
import com.c4pn91.opchallenge.data.remote.model.ResultsDTO
import com.c4pn91.opchallenge.data.remote.model.toKnownForEntity
import com.c4pn91.opchallenge.data.remote.model.toPersonEntity
import com.c4pn91.opchallenge.domain.model.Person
import javax.inject.Inject

class LocalPersonDataSource @Inject constructor(private val personDao: PersonDao) {

    @Throws(Exception::class)
    suspend fun savePersonToDb(apiPerson: ResultsDTO) {
        try {
            val person = apiPerson.toPersonEntity()
            val knownFor = apiPerson.knownForDTO.map { item ->
                item.toKnownForEntity(person.id)
            }

            personDao.deleteAllData()
            personDao.insertPerson(person)
            personDao.insertAllKnownFor(knownFor)
        } catch (e: Exception) {
            throw e
        }
    }

    @Throws(Exception::class)
    suspend fun getPersonFromDb() : Result<Person> {
        return try {
            val result: PersonWithKnownFor = personDao.getPersonWithKnownFor()
            Result.success(result.toPerson())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}