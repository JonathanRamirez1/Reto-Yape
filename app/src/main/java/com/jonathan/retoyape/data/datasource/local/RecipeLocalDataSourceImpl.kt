package com.jonathan.retoyape.data.datasource.local

import com.jonathan.retoyape.data.datasource.local.dao.RecipeDao
import com.jonathan.retoyape.data.datasource.local.entity.RecipeEntity
import javax.inject.Inject

class RecipeLocalDataSourceImpl @Inject constructor(private val recipeDao: RecipeDao): RecipeLocalDataSource {

    override suspend fun getAllRecipes(): List<RecipeEntity>? {
        return recipeDao.getAllRecipes()
    }

    override suspend fun insertAllRecipes(recipes: List<RecipeEntity>?) {
        recipeDao.insertAllRecipes(recipes)
    }
}