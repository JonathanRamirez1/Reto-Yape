package com.jonathan.retoyape.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonathan.retoyape.domain.RecipeUseCase
import com.jonathan.retoyape.domain.model.Recipe
import com.jonathan.retoyape.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val recipeUseCase: RecipeUseCase) : ViewModel() {

    private val _recipes = MutableLiveData<Resource<List<Recipe>>>()
    val recipes: LiveData<Resource<List<Recipe>>> = _recipes

    fun onRecipes() {
        viewModelScope.launch {
            _recipes.postValue(Resource.loading())
            val recipes = recipeUseCase.getAllRecipes()
            if (!recipes.isNullOrEmpty()) {
                _recipes.postValue(Resource.success(recipes))
            } else {
                _recipes.postValue(Resource.error("Ocurrio un error"))
            }
        }
    }
}