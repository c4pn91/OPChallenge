package com.c4pn91.opchallenge.domain.usecase

import android.net.Uri
import com.c4pn91.opchallenge.data.repository_impl.PhotoRepositoryImpl
import javax.inject.Inject

class UploadImagesUseCase @Inject constructor(
    private val repository: PhotoRepositoryImpl
) {
    suspend operator fun invoke(imageUris: List<Uri>): Result<List<String>> {
        return repository.uploadImages(imageUris)
    }
}