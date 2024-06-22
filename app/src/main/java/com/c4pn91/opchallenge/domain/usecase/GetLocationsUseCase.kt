package com.c4pn91.opchallenge.domain.usecase

import com.c4pn91.opchallenge.data.repository_impl.LocationsRepositoryImpl
import com.c4pn91.opchallenge.domain.model.LocationItem
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(
    private val repository: LocationsRepositoryImpl
) {
    suspend operator fun invoke(): Result<List<LocationItem>> {
        return repository.getLocations()
    }
}