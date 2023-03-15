package com.jonathan.retoyape.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jonathan.retoyape.data.datasource.local.dao.RecipeDao
import com.jonathan.retoyape.data.datasource.local.entity.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
}