package com.jonathan.retoyape.domain.model

data class Recipe(
    val image: String? = null,
    val name: String? = null,
    val description: String? = null,
    val locationName: String? = null,
    val latitude: Long? = null,
    val longitude: Long? = null,
)