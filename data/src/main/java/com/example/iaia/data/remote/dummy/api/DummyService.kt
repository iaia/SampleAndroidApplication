package com.example.iaia.data.remote.example.api

import com.example.iaia.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface DummyService {
    @GET("me")
    suspend fun meAsync(): Response<User>
}
