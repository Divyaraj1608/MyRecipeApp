package com.example.recipeapp.data.remote.dto

import kotlinx.serialization.Serializable

// What the server sends BACK after adding
@Serializable
data class AddRecipeResponse(
    val id: Int? = null,
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
