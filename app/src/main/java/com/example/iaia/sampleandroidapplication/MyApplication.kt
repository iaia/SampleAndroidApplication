package com.example.iaia.sampleandroidapplication

import android.app.Application
import com.example.iaia.data.di.apiModule
import com.example.iaia.data.di.databaseModule
import com.example.iaia.data.di.mockApiModule
import com.example.iaia.data.di.repositoryModule
import com.example.iaia.sampleandroidapplication.di.fragmentViewModelModule
import com.example.iaia.sampleandroidapplication.di.viewModelModule
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
                    repositoryModule,
                    databaseModule
                )
            )
        }
    }
}
