package com.example.iaia.sampleandroidapplication.remote.example.api

import com.example.iaia.sampleandroidapplication.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

object DummyApiClient {
    fun build(): DummyService {
        return buildRetrofit().create(DummyService::class.java)
    }

    private fun buildRetrofit(): Retrofit {
        val contentType = MediaType.parse("application/json")!!
        val json = Json.nonstrict
        return Retrofit.Builder().run {
            addConverterFactory(json.asConverterFactory(contentType))
            baseUrl(BuildConfig.DUMMY_API_BASE_URL)
            build()
        }
    }
}
