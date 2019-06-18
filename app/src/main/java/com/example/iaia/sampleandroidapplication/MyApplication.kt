package com.example.iaia.sampleandroidapplication

import android.app.Application
import com.example.iaia.sampleandroidapplication.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    viewModelModule,
                    fragmentViewModelModule,
                    apiModule,
                    mockApiModule,
                    repositoryModule
                )
            )
        }
    }
}
