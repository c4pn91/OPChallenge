package com.c4pn91.opchallenge.domain.repository_inte

import android.net.Uri

interface PhotoRepository {
    suspend fun uploadImages(imageUris: List<Uri>): Result<List<String>>
}