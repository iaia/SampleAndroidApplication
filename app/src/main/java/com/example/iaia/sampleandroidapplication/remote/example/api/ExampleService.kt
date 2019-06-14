package com.example.iaia.sampleandroidapplication.remote.example.api

import com.example.iaia.sampleandroidapplication.data.model.User
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ExampleService {
    @GET("me")
    fun meAsync(): Deferred<User>
}