package com.example.iaia.data.repository

import com.example.iaia.data.model.Employee
import com.example.iaia.data.remote.example.api.DummyService

class EmployeeRepositoryImpl(
    private val service: DummyService
) : EmployeeRepository {
    override suspend fun getEmployees(): List<Employee> {
        return service.employees().let {
            it.body() ?: emptyList()
        }
    }

    override suspend fun getEmployee(id: Int): Employee? {
        return service.employee(id).body()?.let {
            it
        }
    }
}