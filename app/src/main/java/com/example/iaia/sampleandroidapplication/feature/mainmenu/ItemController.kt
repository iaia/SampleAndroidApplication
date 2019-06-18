package com.example.iaia.sampleandroidapplication.feature.mainmenu

import com.airbnb.epoxy.TypedEpoxyController
import com.example.iaia.sampleandroidapplication.data.model.MainItem
import com.example.iaia.sampleandroidapplication.itemMain

class ItemController(
    val viewModel: MainFragmentViewModel
) : TypedEpoxyController<List<MainItem>>() {
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
