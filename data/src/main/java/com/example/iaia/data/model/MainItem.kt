package com.example.iaia.data.model

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
    Settings,
    Dummy,
    License
}
