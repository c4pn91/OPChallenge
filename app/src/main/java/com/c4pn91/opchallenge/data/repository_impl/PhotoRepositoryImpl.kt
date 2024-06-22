package com.c4pn91.opchallenge.data.repository_impl

import android.net.Uri
import com.c4pn91.opchallenge.data.datasource.remote.FirebaseStorageDataSource
import com.c4pn91.opchallenge.domain.repository_inte.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val remoteDataSource: FirebaseStorageDataSource
) : PhotoRepository {

    override suspend fun uploadImages(imageUris: List<Uri>): Result<List<String>> {
        return remoteDataSource.uploadImages(imageUris)
    }
}