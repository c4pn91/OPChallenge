package com.c4pn91.opchallenge.domain.repository_inte

import com.c4pn91.opchallenge.domain.model.Person

interface PersonRepository {
    suspend fun getPerson(): Result<Person>
}