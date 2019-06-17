package com.example.iaia.sampleandroidapplication.remote.example.api

import android.content.Context
import com.example.iaia.sampleandroidapplication.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import java.util.concurrent.TimeUnit

object ExampleApiClient {
    fun build(): ExampleService {
        return buildRetrofit().create(ExampleService::class.java)
    }

    fun buildMock(context: Context): ExampleService {
        val builder = buildRetrofit()

        val behavior = NetworkBehavior.create().apply {
            setDelay(100, TimeUnit.MILLISECONDS)
            setFailurePercent(0)
            setErrorPercent(0)
        }

        val mockRetrofit = MockRetrofit.Builder(builder).run {
            networkBehavior(behavior)
            build()
        }

        val delegate = mockRetrofit.create(ExampleService::class.java)
        return MockExampleService(delegate, context)
    }

    private fun buildRetrofit(): Retrofit {
        val contentType = MediaType.parse("application/json")!!
        val json = Json.nonstrict
        return Retrofit.Builder().run {
            addConverterFactory(json.asConverterFactory(contentType))
            baseUrl(BuildConfig.EXAMPLE_API_BASE_URL)
            build()
        }
    }
}
