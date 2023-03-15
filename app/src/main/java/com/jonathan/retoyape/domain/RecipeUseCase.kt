package com.jonathan.retoyape.domain

import com.jonathan.retoyape.data.repository.RecipeRepository
import com.jonathan.retoyape.di.IoDispatcher
import com.jonathan.retoyape.domain.model.Recipe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun getAllRecipes(): List<Recipe>? = withContext(ioDispatcher) {
        val recipesLocal = recipeRepository.getAllRecipesFromLocal()
        if (recipesLocal.isNullOrEmpty()) {
            val recipesRemote = recipeRepository.getAllRecipesFromRemote()
            recipeRepository.insertAllRecipes(recipesRemote)
            recipeRepository.getAllRecipesFromLocal()
        } else {
            recipesLocal
        }
    }
}