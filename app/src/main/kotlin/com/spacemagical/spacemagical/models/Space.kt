package com.spacemagical.spacemagical.models

data class Space(
    val id: Int,
    val country: Country,
    val state: State,
    val address: String,
    val name: String,
    val imageUrl: String,
    val capacity: Int
)
