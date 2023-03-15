package com.jonathan.retoyape.domain.model

import com.jonathan.retoyape.data.datasource.local.entity.RecipeEntity

data class Recipe(
    val id: Long? = null,
    val image: String? = null,
    val name: String? = null,
    val description: String? = null,
    val locationName: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
)

fun Recipe.toRecipes() = RecipeEntity(
    this.id,
    this.image,
    this.name,
    this.description,
    this.locationName,
    this.latitude,
    this.longitude
)

fun List<Recipe>.toListRecipeEntity() = map { it.toRecipes() }