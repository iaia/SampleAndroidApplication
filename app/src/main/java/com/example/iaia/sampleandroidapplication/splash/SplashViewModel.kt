package com.example.iaia.sampleandroidapplication.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    val command = MutableLiveData<Command>()
    val message = MutableLiveData<String>().apply {
        postValue("SPLASH")
    }

    fun init() {
        viewModelScope.launch(Dispatchers.Default) {
            delay(1 * 1000)
            command.postValue(Command.GoToMain)
        }
    }
}

sealed class Command {
    object GoToMain : Command()
}
