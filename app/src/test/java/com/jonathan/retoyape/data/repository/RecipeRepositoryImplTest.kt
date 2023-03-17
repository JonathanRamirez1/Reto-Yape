package com.jonathan.retoyape.data.repository

import com.jonathan.retoyape.data.datasource.local.RecipeLocalDataSource
import com.jonathan.retoyape.data.datasource.remote.network.RecipeRemoteDatasource
import com.jonathan.retoyape.data.exception.NoInternetException
import com.jonathan.retoyape.domain.model.Recipe
import com.jonathan.retoyape.domain.model.toListRecipeEntity
import com.jonathan.retoyape.domain.model.toListRecipeModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class RecipeRepositoryImplTest {

    private val remoteDataSource = mock(RecipeRemoteDatasource::class.java)
    private val localDataSource = mock(RecipeLocalDataSource::class.java)
    private val repository = RecipeRepositoryImpl(remoteDataSource, localDataSource)

    @Test
    fun `test getAllRecipesFromRemote should return list of recipes`() = runBlocking {
        //Given
        val expectedRecipes = listOf(
            Recipe(1, "Image 1", "Recipe 1", "Description 1", "Location 1", 1.0, 2.0),
            Recipe(2, "Image 2", "Recipe 2", "Description 2", "Location 2", 3.0, 4.0)
        )
        given(remoteDataSource.getRecipesFromApi()).willReturn(expectedRecipes.toListRecipeModel())

        //When
        val result = repository.getAllRecipesFromRemote()

        //Then
        assertEquals(expectedRecipes, result)
    }

    @Test
    fun `getAllRecipesFromRemote should throw exception when no internet connection available`() {
        runBlocking {
            try {
                repository.getAllRecipesFromRemote()
            } catch (e: NoInternetException) {
                fail("Expected NoInternetException but no exception was thrown")
            }
        }
    }


    @Test
    fun `test getAllRecipesFromLocal should return list of recipes`() = runBlocking {
        // Given
        val expectedRecipes = listOf(
            Recipe(1, "Image 1", "Recipe 1", "Description 1", "Location 1", 1.0, 2.0),
            Recipe(2, "Image 2", "Recipe 2", "Description 2", "Location 2", 3.0, 4.0)
        )
        given(localDataSource.getAllRecipes()).willReturn(expectedRecipes.toListRecipeEntity())

        // When
        val result = repository.getAllRecipesFromLocal()

        // Then
        assertEquals(expectedRecipes, result)
    }

    @Test
    fun `test insertAllRecipes should call localDataSource insertAllRecipes`() = runBlocking {
        // Given
        val recipes = listOf(
            Recipe(1, "Image 1", "Recipe 1", "Description 1", "Location 1", 1.0, 2.0),
            Recipe(2, "Image 2", "Recipe 2", "Description 2", "Location 2", 3.0, 4.0)
        )

        // When
        repository.insertAllRecipes(recipes)

        // Then
        verify(localDataSource).insertAllRecipes(recipes.toListRecipeEntity())
    }
}