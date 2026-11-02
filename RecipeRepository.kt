package com.example.recipeapp.domain.repository

import com.example.recipeapp.data.remote.dto.RecipeDto

// Blueprint only.
// The real implementation lives in the data layer.
interface RecipeRepository {
    suspend fun getAllRecipes(): List<RecipeDto>
    suspend fun getRecipeById(id: Int): RecipeDto
}
