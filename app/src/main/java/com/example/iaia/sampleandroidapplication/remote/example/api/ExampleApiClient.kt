package com.example.iaia.sampleandroidapplication.remote.api.example

import com.example.iaia.sampleandroidapplication.BuildConfig
import com.example.iaia.sampleandroidapplication.remote.example.api.ExampleService
import retrofit2.Retrofit

class ExampleApiClient {
    companion object {
        fun build(): ExampleService = Retrofit.Builder().let {
            it.baseUrl(BuildConfig.API_BASE_URL)
            it.build()
        }.create(ExampleService::class.java)
    }
}
