package com.jonathan.retoyape.data.datasource.remote.network

import com.jonathan.retoyape.data.datasource.remote.model.RecipeModel
import javax.inject.Inject

class RecipeRemoteDatasourceImpl @Inject constructor(private val recipeApi: RecipeApi): RecipeRemoteDatasource {

    override suspend fun getRecipesFromApi(): List<RecipeModel>? {
        return recipeApi.getRecipes().body()?.recipes
    }
}