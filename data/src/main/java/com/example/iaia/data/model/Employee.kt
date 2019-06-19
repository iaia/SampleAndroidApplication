package com.example.iaia.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Employee(
    @PrimaryKey
    val id: Int,
    @SerialName("employee_name")
    val name: String,
    @SerialName("employee_salary")
    val salary: Int,
    @SerialName("employee_age")
    val age: Int,
    @SerialName("profile_image")
    val profileImage: String
)