package com.jonathan.retoyape.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class RecipeModel(
    @SerializedName("image") val image: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("location_name") val locationName: String? = null,
    @SerializedName("latitude") val latitude: Long? = null,
    @SerializedName("longitude") val longitude: Long? = null,
)