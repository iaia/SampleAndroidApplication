package com.example.iaia.data.repository

import com.example.iaia.data.db.dao.EmployeeDao
import com.example.iaia.data.model.Employee
import com.example.iaia.data.remote.example.api.DummyService

class EmployeeRepositoryImpl(
    private val service: DummyService,
    private val employeeDao: EmployeeDao
) : EmployeeRepository {
    override suspend fun getEmployees(): List<Employee> {
        var employees = employeeDao.getAll()
        if (employees.isNotEmpty()) return employees

        return service.employees().let {
            employees = it.body() ?: emptyList()
            employeeDao.insert(employees)
            return@let employees
        }
    }

    override suspend fun getEmployee(id: Int): Employee? {
        return service.employee(id).body()?.let {
            it
        }
    }
}