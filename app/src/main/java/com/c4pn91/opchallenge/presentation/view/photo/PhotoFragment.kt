package com.c4pn91.opchallenge.presentation.view.photo

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.c4pn91.opchallenge.databinding.FragmentPhotoBinding
import com.c4pn91.opchallenge.presentation.viewmodel.PhotoState
import com.c4pn91.opchallenge.presentation.viewmodel.PhotoViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotoFragment : Fragment() {

    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding

    private val photoViewModel: PhotoViewModel by viewModels()

    private var snackbar: Snackbar? = null

    private val selectImagesRequest =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris ->
            photoViewModel.uploadImages(uris)
        }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                selectImagesRequest.launch("image/*")
            } else {
                Toast.makeText(context, "Permission denied to read your External storage",
                    Toast.LENGTH_SHORT).show()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.selectImagesButton?.setOnClickListener {
            checkPermission()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            photoViewModel.state.collect { state ->
                when (state) {
                    is PhotoState.Init -> {
                        showSnackbar("")
                    }

                    is PhotoState.Loading -> {
                        showSnackbar("Cargando...")
                    }

                    is PhotoState.Success -> {
                        showSnackbar("")
                        val downloadUrls = photoViewModel.uploadResult.value
                        downloadUrls?.forEach { url ->
                            if (url.length > 1) {
                                showSnackbar("Imágenes subidas con éxito")
                            } else {
                                showSnackbar("Imágen subida con éxito: $url")
                            }
                        }
                    }

                    is PhotoState.Failure -> {
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

    private fun checkPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                selectImagesRequest.launch("image/*")
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
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