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
import com.c4pn91.opchallenge.databinding.FragmentPopularMoviesBinding
import com.c4pn91.opchallenge.presentation.adapter.MoviesAdapter
import com.c4pn91.opchallenge.presentation.viewmodel.PopularMoviesState
import com.c4pn91.opchallenge.presentation.viewmodel.PopularMoviesViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularMoviesFragment : Fragment() {

    private var _binding: FragmentPopularMoviesBinding? = null
    private val binding get() = _binding

    private val popularMoviesViewModel: PopularMoviesViewModel by viewModels()

    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        popularMoviesViewModel.getPopularMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            popularMoviesViewModel.state.collect { state ->
                when (state) {
                    is PopularMoviesState.Init -> {
                        showSnackbar("Iniciando...")
                    }
                    is PopularMoviesState.Loading -> {
                        showSnackbar("Cargando...")
                    }
                    is PopularMoviesState.Success -> {
                        showSnackbar("")
                        val recyclerView = binding?.popularMoviesRecyclerView
                        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
                        recyclerView?.adapter = MoviesAdapter(popularMoviesViewModel.movies.value)
                    }
                    is PopularMoviesState.Failure -> {
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