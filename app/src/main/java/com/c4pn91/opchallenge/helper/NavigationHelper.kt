package com.c4pn91.opchallenge.helper

import android.os.Bundle
import androidx.navigation.NavController
import com.c4pn91.opchallenge.R

class NavigationHelper(
    private val navController: NavController
) {

    fun navigateAccordingToMenuItem(itemId: Int) {
        when (itemId) {
            R.id.personFragment -> navigateTo(R.id.personFragment)
            R.id.moviesFragment -> navigateTo(R.id.moviesFragment)
            R.id.mapFragment -> navigateTo(R.id.mapFragment)
            R.id.photoFragment -> navigateTo(R.id.photoFragment)
        }
    }

    fun navigateTo(destinationId: Int, args: Bundle? = null) {
        navController.navigate(destinationId, args)
    }

    fun navigateBack() {
        navController.navigateUp()
    }
}