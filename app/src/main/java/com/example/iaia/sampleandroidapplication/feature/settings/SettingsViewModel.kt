package com.example.iaia.sampleandroidapplication.feature.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iaia.sampleandroidapplication.data.model.SettingItem
import com.example.iaia.sampleandroidapplication.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    val command = MutableLiveData<Command>()
    val items = MutableLiveData<List<SettingItem>>()

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            items.postValue(
                listOf(
                    SettingItem(userRepository.me().username)
                )
            )
        }
    }
}

sealed class Command {
}
