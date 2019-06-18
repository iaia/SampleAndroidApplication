package com.example.iaia.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val username: String
    //val avatar: String? = null
)