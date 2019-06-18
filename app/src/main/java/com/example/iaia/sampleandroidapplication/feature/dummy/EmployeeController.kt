package com.example.iaia.sampleandroidapplication.feature.dummy

import com.airbnb.epoxy.TypedEpoxyController
import com.example.iaia.data.model.Employee
import com.example.iaia.sampleandroidapplication.itemEmployee

class EmployeeController() : TypedEpoxyController<List<Employee>>() {
    override fun buildModels(data: List<Employee>) {
        data.forEach {
            itemEmployee {
                id(it.id)
                name(it.name)
            }
        }
    }
}
