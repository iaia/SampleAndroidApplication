package com.example.iaia.sampleandroidapplication.feature.dummy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DummyViewModel(
) : ViewModel() {
    val command = MutableLiveData<Command>()

    fun init() {}
}

sealed class Command {
}
