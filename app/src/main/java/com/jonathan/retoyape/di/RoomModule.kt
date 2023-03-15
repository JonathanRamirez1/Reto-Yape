package com.jonathan.retoyape.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RecipeDatabase::class.java, RECIPES_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideItemsDatabase(recipeDatabase: RecipeDatabase) = recipeDatabase.recipeDao()
}