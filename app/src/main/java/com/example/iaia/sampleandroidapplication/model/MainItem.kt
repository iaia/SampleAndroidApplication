package com.example.iaia.sampleandroidapplication.model

data class MainItem(
    val key: ItemKey,
    val title: String
)

enum class ItemKey {
    Camera,
    Fragments,
    List,
    ExoPlayer,
    Chat,
    License
}
