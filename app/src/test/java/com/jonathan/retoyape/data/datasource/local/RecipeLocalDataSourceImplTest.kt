package com.jonathan.retoyape.data.datasource.local

import com.google.common.truth.Truth.assertThat
import com.jonathan.retoyape.data.datasource.local.dao.RecipeDao
import com.jonathan.retoyape.data.datasource.local.entity.RecipeEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RecipeLocalDataSourceImplTest {

    @Mock
    private lateinit var recipeDao: RecipeDao

    private lateinit var recipeLocalDataSourceImpl: RecipeLocalDataSourceImpl

    @Before
    fun setup() {
        recipeLocalDataSourceImpl = RecipeLocalDataSourceImpl(recipeDao)
    }

    @Test
    fun `getAllRecipes should return list of recipes`() = runTest {
        // Given
        val recipeList = listOf(
            RecipeEntity(1, "Image 1", "Recipe 1", "Description 1", "Location 1", 1.0, 2.0),
            RecipeEntity(2, "Image 2", "Recipe 2", "Description 2", "Location 2", 3.0, 4.0)
        )
        `when`(recipeDao.getAllRecipes()).thenReturn(recipeList)

        // When
        val result = recipeLocalDataSourceImpl.getAllRecipes()

        // Then
        assertThat(result).isEqualTo(recipeList)
    }

    @Test
    fun `insertAllRecipes should insert the recipes`() = runTest {
        // Given
        val recipeList = listOf(
            RecipeEntity(1, "Image 1", "Recipe 1", "Description 1", "Location 1", 1.0, 2.0),
            RecipeEntity(2, "Image 2", "Recipe 2", "Description 2", "Location 2", 3.0, 4.0)
        )

        // When
        recipeLocalDataSourceImpl.insertAllRecipes(recipeList)

        // Then
        verify(recipeDao).insertAllRecipes(recipeList)
    }
}