package com.jonathan.retoyape.data.datasource.remote.response

import com.google.gson.annotations.SerializedName
import com.jonathan.retoyape.data.datasource.remote.model.RecipeModel

data class RecipesResponse(@SerializedName("recipes") val recipes: List<RecipeModel>)