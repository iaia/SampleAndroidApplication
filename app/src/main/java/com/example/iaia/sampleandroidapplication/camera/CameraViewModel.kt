package com.example.iaia.sampleandroidapplication.camera

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CameraViewModel: ViewModel() {
    val command = MutableLiveData<Command>()

    fun init() {
    }
}

sealed class Command {
}
