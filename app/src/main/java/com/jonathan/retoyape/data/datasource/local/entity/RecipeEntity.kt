package com.jonathan.retoyape.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jonathan.retoyape.data.datasource.remote.model.RecipeModel
import com.jonathan.retoyape.domain.model.Recipe

@Entity(tableName = "recipes_table")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long?,
    @ColumnInfo(name = "image") val image: String? = null,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "location_name") val locationName: String? = null,
    @ColumnInfo(name = "latitude") val latitude: Double? = null,
    @ColumnInfo(name = "longitude") val longitude: Double? = null,
)

fun RecipeEntity.toRecipes() = Recipe(
    this.id,
    this.image,
    this.name,
    this.description,
    this.locationName,
    this.latitude,
    this.longitude
)

fun List<RecipeEntity>.toListRecipes() = map { it.toRecipes() }
