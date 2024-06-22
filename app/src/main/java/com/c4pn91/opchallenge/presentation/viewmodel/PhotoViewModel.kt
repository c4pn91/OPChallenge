package com.c4pn91.opchallenge.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c4pn91.opchallenge.domain.usecase.UploadImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class PhotoState {
    data object Init                       : PhotoState()
    data object Loading                    : PhotoState()
    data object Success                    : PhotoState()
    data class  Failure(val error: String) : PhotoState()
}


@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val uploadImagesUseCase: UploadImagesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<PhotoState>(PhotoState.Init)
    val state: StateFlow<PhotoState> = _state

    private val _uploadResult = MutableLiveData<List<String>>()
    val uploadResult: LiveData<List<String>> get() = _uploadResult

    fun uploadImages(imageUris: List<Uri>) {
        _state.value = PhotoState.Loading

        viewModelScope.launch {
            val result = uploadImagesUseCase.invoke(imageUris)

            result.onSuccess { uris ->
                _uploadResult.value = uris
                _state.value = PhotoState.Success
            }.onFailure { error ->
                _state.value = PhotoState.Failure(error.message ?: "Unknown error")
            }
        }
    }
}