package com.jonathan.retoyape.data.repository

import com.jonathan.retoyape.domain.model.Recipe

interface RecipeRepository {

    suspend fun getAllRecipesFromRemote(): List<Recipe>?
    suspend fun getAllRecipesFromLocal(): List<Recipe>?
    suspend fun insertAllRecipes(recipes: List<Recipe>?)
}