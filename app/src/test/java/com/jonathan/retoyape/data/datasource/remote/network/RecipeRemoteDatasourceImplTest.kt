package com.jonathan.retoyape.data.datasource.remote.network

import com.jonathan.retoyape.data.datasource.remote.model.RecipeModel
import com.jonathan.retoyape.data.datasource.remote.response.RecipesResponse
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response
import retrofit2.Response.error

@ExperimentalCoroutinesApi
class RecipeRemoteDatasourceImplTest {

    private lateinit var recipeApi: RecipeApi
    private lateinit var recipeRemoteDataSource: RecipeRemoteDatasourceImpl

    @Before
    fun setup() {
        recipeApi = mock(RecipeApi::class.java)
        recipeRemoteDataSource = RecipeRemoteDatasourceImpl(recipeApi)
    }

    @Test
    fun `getRecipesFromApi should return list of RecipeModel`() = runTest {
        // Given
        val recipeModelList: List<RecipeModel> = listOf(RecipeModel(1, "Image 1", "Recipe 1", "Description 1", "Location 1", 1.0, 2.0))
        val recipesResponse = RecipesResponse(recipeModelList)
        val response = Response.success(recipesResponse)
        `when`(recipeApi.getRecipes()).thenReturn(response)

        // When
        val result =  recipeRemoteDataSource.getRecipesFromApi()

        // Then
        assertNotNull(result)
        assertEquals(recipeModelList, result)
    }


    @Test
    fun `getRecipesFromApi should return null when response is unsuccessful`() = runTest {
        // Given
        `when`(recipeApi.getRecipes()).thenReturn(error(404, ResponseBody.create(null, "")))

        // When
        val result = recipeRemoteDataSource.getRecipesFromApi()

        // Then
        assertNull(result)
    }
}