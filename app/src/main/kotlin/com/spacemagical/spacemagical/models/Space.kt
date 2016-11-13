package com.spacemagical.spacemagical.models

data class Space(
    val id: Int,
    val countryId: Int,
    val stateId: Int,
    val cityId: Int,
    val address: String,
    val name: String,
    val imageUrl: String,
    val capacity: Int
)
