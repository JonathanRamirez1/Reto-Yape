package com.jonathan.retoyape.data.datasource.remote.network

import com.jonathan.retoyape.data.datasource.remote.response.RecipesResponse
import retrofit2.Response
import retrofit2.http.GET

interface RecipeApi {

    @GET("/recipes")
    suspend fun getRecipes(): Response<RecipesResponse>
}