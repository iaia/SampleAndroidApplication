package com.example.iaia.data.remote.example.api

import com.example.iaia.data.model.Employee
import retrofit2.Response
import retrofit2.http.GET

interface DummyService {
    @GET("employees")
    suspend fun employees(): Response<List<Employee>>

    @GET("employee/{id}")
    suspend fun employee(id: Int): Response<Employee>
}
