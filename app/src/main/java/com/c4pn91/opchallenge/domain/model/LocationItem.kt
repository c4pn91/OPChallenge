package com.c4pn91.opchallenge.domain.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.GeoPoint

data class LocationItem(
    val date: String,
    val latlon: GeoPoint
)