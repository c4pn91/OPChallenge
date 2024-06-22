package com.c4pn91.opchallenge.data.datasource.remote

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseStorageDataSource @Inject constructor(
    private val storage: FirebaseStorage
) {

    suspend fun uploadImages(imageUris: List<Uri>): Result<List<String>> {
        return withContext(Dispatchers.IO) {
            try {
                val downloadUrls = mutableListOf<String>()
                imageUris.forEach { uri ->
                    val ref = storage.reference.child("images/${uri.lastPathSegment}")
                    val uploadTask = ref.putFile(uri).await()
                    val downloadUrl = ref.downloadUrl.await().toString()
                    downloadUrls.add(downloadUrl)
                }
                Result.success(downloadUrls)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}