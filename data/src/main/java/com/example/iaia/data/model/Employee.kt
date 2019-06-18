package com.example.iaia.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Employee(
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