package com.jonathan.retoyape.data.datasource.remote.model

import com.google.gson.annotations.SerializedName
import com.jonathan.retoyape.domain.model.Recipe

data class RecipeModel(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("location_name") val locationName: String? = null,
    @SerializedName("latitude") val latitude: Long? = null,
    @SerializedName("longitude") val longitude: Long? = null,
)

fun RecipeModel.toRecipes() = Recipe(
    this.id,
    this.image,
    this.name,
    this.description,
    this.locationName,
    this.latitude,
    this.longitude
)

fun List<RecipeModel>.toListRecipes() = map { it.toRecipes() }