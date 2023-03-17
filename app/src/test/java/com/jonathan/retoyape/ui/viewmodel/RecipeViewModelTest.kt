package com.jonathan.retoyape.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.jonathan.retoyape.domain.RecipeUseCase
import com.jonathan.retoyape.domain.model.Recipe
import com.jonathan.retoyape.utils.LiveDataTestUtil
import com.jonathan.retoyape.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
class RecipeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: RecipeViewModel
    private val recipeUseCase = mockk<RecipeUseCase>()
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = RecipeViewModel(recipeUseCase)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `onRecipes should return error when getAllRecipes returns null or empty`() = runTest {
        // Given
        val emptyRecipeList = emptyList<Recipe>()
        coEvery { recipeUseCase.getAllRecipes() } returns emptyRecipeList

        // When
        viewModel.onRecipes()

        // Then
        val recipesValue = LiveDataTestUtil.getOrAwaitValue(viewModel.recipes)
        assertThat(recipesValue.status).isEqualTo(Resource.Status.ERROR)
    }

    @Test
    fun `onRecipes should return success when getAllRecipes returns a list of recipes`() = runTest {
        // Given
        val recipeList = listOf(Recipe(1, "Image 1", "Recipe 1", "Description 1", "Location 1", 1.0, 2.0))
        coEvery { recipeUseCase.getAllRecipes() } returns recipeList

        // When
        viewModel.onRecipes()

        // Then
        val recipesValue = LiveDataTestUtil.getOrAwaitValue(viewModel.recipes)
        assertThat(recipesValue.status).isEqualTo(Resource.Status.SUCCESS)
        assertThat(recipesValue.data).isEqualTo(recipeList)
    }
}
