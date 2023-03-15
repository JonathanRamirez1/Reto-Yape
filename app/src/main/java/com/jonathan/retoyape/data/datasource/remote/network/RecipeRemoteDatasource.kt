package com.jonathan.retoyape.data.datasource.remote.network

import com.jonathan.retoyape.data.datasource.remote.model.RecipeModel

interface RecipeRemoteDatasource {

    suspend fun getRecipesFromApi(): List<RecipeModel>?
}