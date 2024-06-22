package com.c4pn91.opchallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c4pn91.opchallenge.domain.model.LocationItem
import com.c4pn91.opchallenge.domain.usecase.GetLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class MapState {
    data object Init                       : MapState()
    data object Loading                    : MapState()
    data object Success                    : MapState()
    data class  Failure(val error: String) : MapState()
}

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getLocationsUseCase: GetLocationsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MapState>(MapState.Init)
    val state: StateFlow<MapState> = _state

    private val _locations = MutableLiveData<List<LocationItem>>()
    val locations: LiveData<List<LocationItem>> get() = _locations

    fun getLocations() {
        viewModelScope.launch {
            val result = getLocationsUseCase.invoke()

            result.onSuccess { locations ->
                _locations.value = locations
                _state.value = MapState.Success
            }.onFailure { error ->
                _state.value = MapState.Failure(error.message ?: "Unknown error")
            }
        }
    }
}
