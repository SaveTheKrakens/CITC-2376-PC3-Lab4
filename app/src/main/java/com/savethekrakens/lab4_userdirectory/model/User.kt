package com.savethekrakens.lab4_userdirectory.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: Name,
    val picture: Picture
)

@Serializable
data class Results(
    val results: List<User>
)

@Serializable
data class Name (
    val first: String,
    val last: String
)

@Serializable
data class Picture(
    val large: String,
    val medium: String,
    val small: String
)