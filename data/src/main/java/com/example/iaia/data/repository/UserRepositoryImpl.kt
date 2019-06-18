package com.example.iaia.sampleandroidapplication.data.repository

import com.example.iaia.data.model.User
import com.example.iaia.data.remote.example.api.ExampleService

class UserRepositoryImpl(
    private val exampleService: ExampleService
) : UserRepository {
    override suspend fun me(): User {
        exampleService.meAsync().body().apply {
            return this ?: User(0, "xxx")
        }
    }
}