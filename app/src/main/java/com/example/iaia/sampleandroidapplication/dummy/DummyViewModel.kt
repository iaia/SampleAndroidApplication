package com.example.iaia.sampleandroidapplication.dummy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DummyViewModel(
) : ViewModel() {
    val command = MutableLiveData<Command>()

    fun init() {}
}

sealed class Command {
}
