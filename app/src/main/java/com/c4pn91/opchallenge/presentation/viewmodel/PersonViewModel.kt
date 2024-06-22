package com.c4pn91.opchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c4pn91.opchallenge.domain.model.Person
import com.c4pn91.opchallenge.domain.model.toEmpty
import com.c4pn91.opchallenge.domain.usecase.GetPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class PersonState {
    data object Init                       : PersonState()
    data object Loading                    : PersonState()
    data object Success                    : PersonState()
    data class  Failure(val error: String) : PersonState()
}

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val getPersonUseCase: GetPersonUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<PersonState>(PersonState.Init)
    val state: StateFlow<PersonState> = _state

    private val _person = MutableStateFlow<Person>(Person().toEmpty())
    val person: StateFlow<Person> = _person

    fun getPerson() {
        _state.value = PersonState.Loading

        viewModelScope.launch {
            val result = getPersonUseCase.invoke()

            result.onSuccess { person ->
                _person.value = person
                _state.value = PersonState.Success
            }.onFailure { error ->
                _state.value = PersonState.Failure(error.message ?: "Unknown error")
            }
        }
    }
}