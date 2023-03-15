package com.jonathan.retoyape.di

import com.jonathan.retoyape.data.datasource.remote.network.RecipeRemoteDatasource
import com.jonathan.retoyape.data.datasource.remote.network.RecipeRemoteDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(recipeRemoteDataSourceImpl: RecipeRemoteDatasourceImpl): RecipeRemoteDatasource = recipeRemoteDataSourceImpl
}