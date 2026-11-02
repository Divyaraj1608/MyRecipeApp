package com.example.recipeapp.data.remote.dto

import kotlinx.serialization.Serializable

// What the app SENDS when the user adds a recipe.
@Serializable
data class AddRecipeRequest(
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val servings: Int,
    val difficulty: String,
    val cuisine: String,
    val caloriesPerServing: Int,
    val tags: List<String>,
    val mealType: List<String>
)