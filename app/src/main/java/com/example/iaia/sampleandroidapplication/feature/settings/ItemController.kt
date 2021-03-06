package com.example.iaia.sampleandroidapplication.feature.settings

import com.airbnb.epoxy.TypedEpoxyController
import com.example.iaia.data.model.SettingItem
import com.example.iaia.sampleandroidapplication.itemSetting

class ItemController() : TypedEpoxyController<List<SettingItem>>() {
    override fun buildModels(data: List<SettingItem>) {
        data.forEach {
            itemSetting {
                id(modelCountBuiltSoFar)
                title(it.title)
            }
        }
    }
}
