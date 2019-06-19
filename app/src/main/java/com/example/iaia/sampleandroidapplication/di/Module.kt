package com.example.iaia.sampleandroidapplication.di

import com.example.iaia.sampleandroidapplication.feature.camera.CameraViewModel
import com.example.iaia.sampleandroidapplication.feature.dummy.DummyViewModel
import com.example.iaia.sampleandroidapplication.feature.main.MainViewModel
import com.example.iaia.sampleandroidapplication.feature.mainmenu.MainFragmentViewModel
import com.example.iaia.sampleandroidapplication.feature.settings.SettingsViewModel
import com.example.iaia.sampleandroidapplication.feature.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { MainViewModel() }
    viewModel { SettingsViewModel(get()) }
    viewModel { DummyViewModel(get()) }
}

val fragmentViewModelModule = module {
    viewModel { MainFragmentViewModel() }
    viewModel { CameraViewModel() }
}
