package com.jonathan.retoyape.data.datasource.remote.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.jonathan.retoyape.di.NetworkModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@SmallTest
@UninstallModules(NetworkModule::class)
class RecipeRemoteDatasourceImplTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var recipeApi: RecipeApi
    private lateinit var mockWebServer: MockWebServer
    private lateinit var dataSource: RecipeRemoteDatasourceImpl

    @Before
    fun setup() {
        hiltRule.inject()
        mockWebServer = MockWebServer()
        recipeApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApi::class.java)
        dataSource = RecipeRemoteDatasourceImpl(recipeApi)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getRecipesFromApiShouldReturnListOfRecipeModel() = runTest {
        // Given
        val responseBody = """
            {
               "recipes": [
                    {
                      "image": "Image1",
                      "name": "Name1",
                      "description": "Description1",
                      "location_name": "LocationName1",
                      "latitude": 37.7749,
                      "longitude": -122.4194
                   },
                   {
                      "image": "Image2",
                      "name": "Name2",
                      "description": "Description2",
                      "location_name": "LocationName2",
                      "latitude": 45.4642,
                      "longitude": 9.1900
                   }
               ]
            }
        """
        mockWebServer.enqueue(MockResponse().setBody(responseBody))

        // When
        val result = dataSource.getRecipesFromApi()

        // Then
        assertThat(result).isNotNull()
        assertThat(result).hasSize(2)
        assertThat(result?.get(0)?.image).isEqualTo("Image1")
        assertThat(result?.get(0)?.name).isEqualTo("Name1")
        assertThat(result?.get(0)?.description).isEqualTo("Description1")
        assertThat(result?.get(0)?.locationName).isEqualTo("LocationName1")
        assertThat(result?.get(0)?.latitude).isEqualTo(37.7749)
        assertThat(result?.get(0)?.longitude).isEqualTo(-122.4194)
        assertThat(result?.get(1)?.image).isEqualTo("Image2")
        assertThat(result?.get(1)?.name).isEqualTo("Name2")
        assertThat(result?.get(1)?.description).isEqualTo("Description2")
        assertThat(result?.get(1)?.locationName).isEqualTo("LocationName2")
        assertThat(result?.get(1)?.latitude).isEqualTo(45.4642)
        assertThat(result?.get(1)?.longitude).isEqualTo(9.1900)
    }
}