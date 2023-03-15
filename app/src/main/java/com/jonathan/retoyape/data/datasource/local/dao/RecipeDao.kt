package com.jonathan.retoyape.data.datasource.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jonathan.retoyape.data.datasource.local.entity.RecipeEntity

interface RecipeDao {

    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    suspend fun getAllRecipes(): List<RecipeEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRecipes(recipes: List<RecipeEntity>?)
}