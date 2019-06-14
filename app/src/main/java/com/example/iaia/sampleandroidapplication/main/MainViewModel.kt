package com.example.iaia.sampleandroidapplication.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iaia.sampleandroidapplication.data.model.ItemKey
import com.example.iaia.sampleandroidapplication.data.model.MainItem

class MainViewModel : ViewModel() {
    val command = MutableLiveData<Command>()
    val items = MutableLiveData<List<MainItem>>()

    fun init() {
        items.postValue(ItemKey.values().map {
            MainItem(it, it.toString())
        })
    }

    fun onClickItem(key: ItemKey) {
        command.postValue(
            when (key) {
                ItemKey.Camera -> Command.GoToCamera
                ItemKey.License -> Command.GoToLicense
                ItemKey.Settings -> Command.GoToSettings
                else -> Command.GoToFragments
            }
        )
    }
}

sealed class Command {
    object GoToCamera : Command()
    object GoToFragments : Command()
    object GoToLicense : Command()
    object GoToSettings : Command()
}
