package com.c4pn91.opchallenge.domain.usecase

import com.c4pn91.opchallenge.data.repository_impl.PersonRepositoryImpl
import com.c4pn91.opchallenge.domain.model.Person
import javax.inject.Inject

class GetPersonUseCase @Inject constructor(
    private val personRepositoryImpl: PersonRepositoryImpl
) {
    suspend operator fun invoke() : Result<Person> {
        return personRepositoryImpl.getPerson()
    }
}