package com.jonathan.retoyape.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes_table")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long?,
    @ColumnInfo(name = "image") val image: String? = null,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "location_name") val locationName: String? = null,
    @ColumnInfo(name = "latitude") val latitude: Long? = null,
    @ColumnInfo(name = "longitude") val longitude: Long? = null,
)
