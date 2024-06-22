package com.c4pn91.opchallenge.presentation.view.person

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
import com.c4pn91.opchallenge.databinding.FragmentPersonBinding
import com.c4pn91.opchallenge.presentation.adapter.PersonMoviesAdapter
import com.c4pn91.opchallenge.presentation.viewmodel.PersonState
import com.c4pn91.opchallenge.presentation.viewmodel.PersonViewModel
import com.c4pn91.opchallenge.util.Constants
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PersonFragment : Fragment() {

    private var _binding: FragmentPersonBinding? = null
    private val binding get() = _binding

    private val personViewModel: PersonViewModel by viewModels()

    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        personViewModel.getPerson()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewLifecycleOwner.lifecycleScope.launch {
            personViewModel.state.collect { state ->
                when (state) {
                    is PersonState.Init -> {
                        showSnackbar("Iniciando...")
                    }
                    is PersonState.Loading -> {
                        showSnackbar("Cargando...")
                    }
                    is PersonState.Success -> {
                        showSnackbar("")
                        Glide.with(requireActivity())
                            .load("${Constants.BASE_IMGS_SMALL}${personViewModel.person.value.profilePath}")
                            .apply(RequestOptions().centerCrop())
                            .transform(CircleCrop())
                            .into(binding!!.profileImage)


                        binding?.tvName?.text = personViewModel.person.value.name
                        binding?.tvOriginalName?.text = personViewModel.person.value.originalName
                        binding?.tvGender?.text = if (personViewModel.person.value.gender == 1) "Femenino" else "Masculino"
                        binding?.tvJob?.text = personViewModel.person.value.knownForDepartment
                        binding?.tvPopularity?.text = personViewModel.person.value.popularity?.toString()


                        val recyclerView = binding?.personMoviesRecyclerView
                        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
                        recyclerView?.adapter = PersonMoviesAdapter(personViewModel.person.value.knownFor)
                    }
                    is PersonState.Failure -> {
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