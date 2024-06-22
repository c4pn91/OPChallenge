package com.c4pn91.opchallenge.presentation.view.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.c4pn91.opchallenge.R
import com.c4pn91.opchallenge.databinding.ActivityMainBinding
import com.c4pn91.opchallenge.helper.NavigationHelper
import com.c4pn91.opchallenge.presentation.viewmodel.MainViewModel
import com.c4pn91.opchallenge.presentation.viewmodel.PersonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var navigationHelper: NavigationHelper
    private lateinit var binding: ActivityMainBinding
    private var isNavigationDestinationChange = false

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
        setupBottomNavigation()
        setChangeIconBottomNav()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navigationHelper = NavigationHelper(navController)
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            if (!isNavigationDestinationChange) {
                isNavigationDestinationChange = true
                navigationHelper.navigateAccordingToMenuItem(item.itemId)
            }
            true
        }
    }

    private fun setChangeIconBottomNav() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            isNavigationDestinationChange = true
            val currentDestinationId = destination.id

            when (currentDestinationId) {
                R.id.personFragment -> binding.bottomNavigation.selectedItemId = R.id.personFragment
                R.id.moviesFragment -> binding.bottomNavigation.selectedItemId = R.id.moviesFragment
                R.id.mapFragment -> binding.bottomNavigation.selectedItemId = R.id.mapFragment
                R.id.photoFragment -> binding.bottomNavigation.selectedItemId = R.id.photoFragment
            }

            isNavigationDestinationChange = false
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}