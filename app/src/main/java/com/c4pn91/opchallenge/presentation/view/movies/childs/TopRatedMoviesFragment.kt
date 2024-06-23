package com.c4pn91.opchallenge.presentation.view.movies.childs

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
import com.c4pn91.opchallenge.databinding.FragmentTopRatedMoviesBinding
import com.c4pn91.opchallenge.presentation.adapter.MoviesAdapter
import com.c4pn91.opchallenge.presentation.viewmodel.TopRatedMoviesState
import com.c4pn91.opchallenge.presentation.viewmodel.TopRatedMoviesViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopRatedMoviesFragment : Fragment() {

    private var _binding: FragmentTopRatedMoviesBinding? = null
    private val binding get() = _binding

    private val topRatedMoviesViewModel: TopRatedMoviesViewModel by viewModels()

    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        topRatedMoviesViewModel.getTopRatedMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopRatedMoviesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            topRatedMoviesViewModel.state.collect { state ->
                when (state) {
                    is TopRatedMoviesState.Init -> {
                        showSnackbar("Iniciando...")
                    }
                    is TopRatedMoviesState.Loading -> {
                        showSnackbar("Cargando...")
                    }
                    is TopRatedMoviesState.Success -> {
                        showSnackbar("")
                        val recyclerView = binding?.topRatedMoviesRecyclerView
                        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
                        recyclerView?.adapter = MoviesAdapter(topRatedMoviesViewModel.movies.value)
                    }
                    is TopRatedMoviesState.Failure -> {
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