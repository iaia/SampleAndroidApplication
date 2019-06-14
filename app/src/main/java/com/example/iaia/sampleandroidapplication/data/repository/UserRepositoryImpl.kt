package com.example.iaia.sampleandroidapplication.data.repository

import com.example.iaia.sampleandroidapplication.data.model.User
import com.example.iaia.sampleandroidapplication.remote.example.api.ExampleService

class UserRepositoryImpl(
    private val exampleService: ExampleService
) : UserRepository {
    override suspend fun me(): User {
        exampleService.meAsync().await().apply {
            return this
        }
    }
}