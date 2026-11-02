package com.example.recipeapp.data.repository

import com.example.recipeapp.data.remote.RecipeApiService
import com.example.recipeapp.data.remote.dto.RecipeDto
import com.example.recipeapp.domain.repository.RecipeRepository

class RecipeRepositoryImpl(private val apiService: RecipeApiService
) : RecipeRepository {

    // The response also has total/skip/limit - we only need the list
    override suspend fun getAllRecipes(): List<RecipeDto> {
        return apiService.getAllRecipes().recipes
    }

    override suspend fun getRecipeById(id: Int): RecipeDto {
        return apiService.getRecipeById(id)
    }
}




