package com.jonathan.retoyape.domain

import com.jonathan.retoyape.data.repository.RecipeRepository
import com.jonathan.retoyape.domain.model.Recipe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.*
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class RecipeUseCaseTest {

    private val repository = mock(RecipeRepository::class.java)
    private val ioDispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
    private val useCase = RecipeUseCase(repository, ioDispatcher)

    @Test
    fun `getAllRecipes should return expected list of recipes`() = runBlocking {
        // Given
        val localRecipes = listOf(Recipe(1, "Image 1", "Recipe 1", "Description 1", "Location 1", 1.0, 2.0))
        val remoteRecipes = listOf(Recipe(2, "Image 2", "Recipe 2", "Description 2", "Location 2", 3.0, 4.0))
        BDDMockito.given(repository.getAllRecipesFromLocal()).willReturn(localRecipes)
        BDDMockito.given(repository.getAllRecipesFromRemote()).willReturn(remoteRecipes)
        BDDMockito.given(repository.insertAllRecipes(remoteRecipes)).willReturn(Unit)

        // When
        val result = useCase.getAllRecipes()

        // Then
        assertEquals(localRecipes, result)
        Mockito.verify(repository, Mockito.times(1)).getAllRecipesFromLocal()
        Mockito.verify(repository, Mockito.never()).insertAllRecipes(Mockito.any())
    }
}