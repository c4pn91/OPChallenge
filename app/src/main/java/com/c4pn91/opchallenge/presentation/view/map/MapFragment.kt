package com.c4pn91.opchallenge.presentation.view.map

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.c4pn91.opchallenge.R
import com.c4pn91.opchallenge.databinding.FragmentMapBinding
import com.c4pn91.opchallenge.databinding.FragmentPopularMoviesBinding
import com.c4pn91.opchallenge.presentation.adapter.PersonMoviesAdapter
import com.c4pn91.opchallenge.presentation.viewmodel.MapState
import com.c4pn91.opchallenge.presentation.viewmodel.MapViewModel
import com.c4pn91.opchallenge.presentation.viewmodel.PersonState
import com.c4pn91.opchallenge.presentation.viewmodel.PopularMoviesViewModel
import com.c4pn91.opchallenge.util.Constants
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding

    private val mapViewModel: MapViewModel by viewModels()

    private var snackbar: Snackbar? = null
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)

        val mapFragment = SupportMapFragment.newInstance()
        childFragmentManager.beginTransaction()
            .replace(R.id.map_container, mapFragment)
            .commit()

        mapFragment.getMapAsync(this)

        return binding?.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        mapViewModel.getLocations()

        viewLifecycleOwner.lifecycleScope.launch {
            mapViewModel.state.collect { state ->
                when (state) {
                    is MapState.Init -> {
                        showSnackbar("Iniciando...")
                    }

                    is MapState.Loading -> {
                        showSnackbar("Cargando...")
                    }

                    is MapState.Success -> {
                        showSnackbar("")
                        val locations = mapViewModel.locations.value
                        locations?.forEach { locationItem ->
                            val position =
                                LatLng(locationItem.latlon.latitude, locationItem.latlon.longitude)
                            googleMap.addMarker(
                                MarkerOptions()
                                    .position(position)
                                    .title(locationItem.date)
                            )
                        }
                    }

                    is MapState.Failure -> {
                        showSnackbar(state.error)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        showSnackbar("")
    }

    private fun showSnackbar(message: String) {
        snackbar?.dismiss()

        if (message.isEmpty()) return

        snackbar = Snackbar.make(requireActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_INDEFINITE)
        val snackbarView = snackbar?.view
        val params = snackbarView?.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        snackbarView.layoutParams = params
        snackbar?.show()
    }

}