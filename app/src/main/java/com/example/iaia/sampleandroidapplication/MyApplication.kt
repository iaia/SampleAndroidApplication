package com.example.iaia.sampleandroidapplication

import android.app.Application
import com.example.iaia.sampleandroidapplication.di.apiModule
import com.example.iaia.sampleandroidapplication.di.repositoryModule
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
                    if (BuildConfig.DEBUG) apiModule else apiModule,
                    repositoryModule
                )
            )
        }
    }
}
