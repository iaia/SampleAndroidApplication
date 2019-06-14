package com.example.iaia.sampleandroidapplication.di

import com.example.iaia.sampleandroidapplication.camera.CameraViewModel
import com.example.iaia.sampleandroidapplication.data.repository.UserRepositoryImpl
import com.example.iaia.sampleandroidapplication.main.MainViewModel
import com.example.iaia.sampleandroidapplication.remote.api.example.ExampleApiClient
import com.example.iaia.sampleandroidapplication.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { MainViewModel() }
    viewModel { CameraViewModel() }
}

val mockApiModule = module {
    single { ExampleApiClient.build() }
}

val repositoryModule = module {
    single { UserRepositoryImpl(get()) }
}
