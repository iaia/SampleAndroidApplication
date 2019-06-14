package com.example.iaia.sampleandroidapplication.remote.example.api

import com.example.iaia.sampleandroidapplication.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ExampleService {
    @GET("me")
    suspend fun meAsync(): Response<User>
}
