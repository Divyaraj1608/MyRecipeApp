package com.example.recipeapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RecipesResponse(
    val recipes: List<RecipeDto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)