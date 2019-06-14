package com.example.iaia.sampleandroidapplication.di

import com.example.iaia.sampleandroidapplication.camera.CameraViewModel
import com.example.iaia.sampleandroidapplication.data.repository.UserRepository
import com.example.iaia.sampleandroidapplication.data.repository.UserRepositoryImpl
import com.example.iaia.sampleandroidapplication.main.MainViewModel
import com.example.iaia.sampleandroidapplication.remote.example.api.ExampleApiClient
import com.example.iaia.sampleandroidapplication.settings.SettingsViewModel
import com.example.iaia.sampleandroidapplication.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { MainViewModel() }
    viewModel { CameraViewModel() }
    viewModel { SettingsViewModel(get()) }
}

val mockApiModule = module {
    single { ExampleApiClient.buildMock(get()) }
}

val repositoryModule = module {
    single { UserRepositoryImpl(get()) as UserRepository }
}
