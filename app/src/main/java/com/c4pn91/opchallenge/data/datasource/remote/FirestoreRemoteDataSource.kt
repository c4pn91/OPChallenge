package com.c4pn91.opchallenge.data.datasource.remote

import com.c4pn91.opchallenge.data.remote.model.LocationItemDTO
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreRemoteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    suspend fun getLocations(): List<LocationItemDTO> {
        return try {
            val snapshot = firestore.collection("locations").document("Ucc1EXE4bBiJOxUWNspM").get().await()
            val items = snapshot.get("items") as? List<Map<String, Any>> ?: emptyList()
            val locations = items.map {
                val date = it["date"] as Timestamp
                val latlon = it["latlon"] as GeoPoint
                LocationItemDTO(date, latlon)
            }
            locations
        } catch (error: Exception) {
            throw error
        }
    }
}