package com.example.iaia.sampleandroidapplication.main

import com.airbnb.epoxy.TypedEpoxyController
import com.example.iaia.sampleandroidapplication.itemMain
import com.example.iaia.sampleandroidapplication.model.MainItem

class ItemController(
    val viewModel: MainViewModel
): TypedEpoxyController<List<MainItem>>() {
    override fun buildModels(data: List<MainItem>) {
        data.forEach {
            itemMain {
                id(it.key.name)
                key(it.key)
                title(it.title)
                viewModel(viewModel)
            }
        }
    }
}
