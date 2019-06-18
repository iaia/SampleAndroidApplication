package com.example.iaia.sampleandroidapplication.feature.camera

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraViewModel : ViewModel() {
    val command = MutableLiveData<Command>()

    fun init() {}
    fun capture() {
        command.postValue(Command.Capture)
    }
}

sealed class Command {
    object Capture : Command()
}
