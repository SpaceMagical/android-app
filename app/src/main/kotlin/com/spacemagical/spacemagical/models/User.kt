package com.spacemagical.spacemagical.models

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val iconUrl: String,
    val coverUrl: String,
    val jobType: JobType
)
