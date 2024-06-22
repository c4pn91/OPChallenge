package com.c4pn91.opchallenge.domain.repository_inte

import com.c4pn91.opchallenge.domain.model.LocationItem

interface LocationsRepository {
    suspend fun getLocations(): Result<List<LocationItem>>
}