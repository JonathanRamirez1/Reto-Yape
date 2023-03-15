package com.jonathan.retoyape.data.repository

import com.jonathan.retoyape.data.datasource.remote.network.RecipeRemoteDatasource
import com.jonathan.retoyape.data.datasource.local.RecipeLocalDataSource
import com.jonathan.retoyape.data.datasource.local.entity.toListRecipes
import com.jonathan.retoyape.data.datasource.remote.model.RecipeModel
import com.jonathan.retoyape.data.datasource.remote.model.toListRecipes
import com.jonathan.retoyape.data.exception.NoInternetException
import com.jonathan.retoyape.domain.model.Recipe
import com.jonathan.retoyape.domain.model.toListRecipeEntity
import com.jonathan.retoyape.utils.InternetCheck
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RecipeRemoteDatasource,
    private val localDataSource: RecipeLocalDataSource
): RecipeRepository {

    override suspend fun getAllRecipesFromRemote(): List<Recipe>? {
        if (!InternetCheck.isNetworkAvailable()) {
            throw NoInternetException("No hay conexión a internet disponible")
        }
        val response: List<RecipeModel>? = remoteDataSource.getRecipesFromApi()
        return response?.toListRecipes()
    }

    override suspend fun getAllRecipesFromLocal(): List<Recipe>? {
        return localDataSource.getAllRecipes()?.toListRecipes()
    }

    override suspend fun insertAllRecipes(recipes: List<Recipe>?) {
        localDataSource.insertAllRecipes(recipes.toListRecipeEntity())
    }
}