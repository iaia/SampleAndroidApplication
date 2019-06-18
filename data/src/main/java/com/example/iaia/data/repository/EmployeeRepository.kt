package com.example.iaia.data.repository

import com.example.iaia.data.model.Employee

interface EmployeeRepository {
    suspend fun getEmployees(): List<Employee>
    suspend fun getEmployee(id: Int): Employee?
}