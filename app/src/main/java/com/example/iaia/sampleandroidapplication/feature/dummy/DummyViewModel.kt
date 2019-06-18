package com.example.iaia.sampleandroidapplication.feature.dummy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iaia.data.model.Employee
import com.example.iaia.data.repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DummyViewModel(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {
    val command = MutableLiveData<Command>()
    val employees = MutableLiveData<List<Employee>>()

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            employees.postValue(employeeRepository.getEmployees())
        }
    }
}

sealed class Command {
}
