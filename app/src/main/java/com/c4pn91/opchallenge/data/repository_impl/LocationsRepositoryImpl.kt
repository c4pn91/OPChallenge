package com.c4pn91.opchallenge.data.repository_impl

import com.c4pn91.opchallenge.data.datasource.remote.FirestoreRemoteDataSource
import com.c4pn91.opchallenge.data.remote.model.LocationItemDTO
import com.c4pn91.opchallenge.data.remote.model.toLocationItem
import com.c4pn91.opchallenge.domain.model.LocationItem
import com.c4pn91.opchallenge.domain.repository_inte.LocationsRepository
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private val remoteDataSource: FirestoreRemoteDataSource
) : LocationsRepository {

    override suspend fun getLocations(): Result<List<LocationItem>> {
        return try {
            val resultLocations : List<LocationItemDTO> = remoteDataSource.getLocations()
            if (resultLocations.isNotEmpty()) {
                val locations = resultLocations.map { it.toLocationItem() }
                Result.success(locations)
            } else {
                return Result.failure(Throwable("Locations are empty"))
            }
        } catch (error: Exception) {
            Result.failure(Throwable(error.message ?: "Error get locations"))
        }
    }
}