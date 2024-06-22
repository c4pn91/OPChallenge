package com.c4pn91.opchallenge.presentation.viewmodel

import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val currentFragmentId = MutableLiveData<MenuItem>()

    fun setCurrentFragmentId(fragmentId: MenuItem) {
        currentFragmentId.value = fragmentId
    }

    fun getCurrentFragmentId(): LiveData<MenuItem> {
        return currentFragmentId
    }
}
