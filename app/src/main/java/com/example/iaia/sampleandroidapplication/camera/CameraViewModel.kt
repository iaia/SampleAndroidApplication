package com.example.iaia.sampleandroidapplication.camera

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraViewModel : ViewModel() {
    val command = MutableLiveData<Command>()

    fun init() {}
}

sealed class Command {
}
