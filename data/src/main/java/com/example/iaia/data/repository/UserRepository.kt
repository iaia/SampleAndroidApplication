package com.example.iaia.sampleandroidapplication.data.repository

import com.example.iaia.data.model.User

interface UserRepository {
    suspend fun me(): User
}