package com.c4pn91.opchallenge.data.remote.model

import com.c4pn91.opchallenge.domain.model.LocationItem
import com.c4pn91.opchallenge.util.Formats
import com.google.firebase.Timestamp
import com.google.firebase.firestore.GeoPoint

data class LocationItemDTO(
    val date: Timestamp,
    val latlon: GeoPoint
)

fun LocationItemDTO.toLocationItem() = LocationItem(
    date = Formats.formatDate(date),
    latlon = latlon
)