package com.example.iaia.sampleandroidapplication.module

import com.example.iaia.sampleandroidapplication.main.MainViewModel
import com.example.iaia.sampleandroidapplication.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // single instance of HelloRepository
    // single<HelloRepository> { HelloRepositoryImpl() }

    viewModel { SplashViewModel() }
    viewModel { MainViewModel() }
}
