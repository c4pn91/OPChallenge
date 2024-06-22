package com.c4pn91.opchallenge.util

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Formats {
    fun formatDate(timestamp: Timestamp): String {
        val date: Date = timestamp.toDate()
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US)

        return formatter.format(date)
    }
}