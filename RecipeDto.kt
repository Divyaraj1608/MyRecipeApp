package com.example.recipeapp.data.remote.dto

import kotlinx.serialization.Serializable

// Shape of ONE recipe in the dummyjson.com/recipes response.
// (Generated with the "JSON to Kotlin" plugin, then split into its own file.)
// Property names must match the JSON keys exactly (or use @SerialName).
@Serializable
data class RecipeDto(
    val id: Int,
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
    val userId: Int,
    val image: String,
    val rating: Double,
    val reviewCount: Int,
    val mealType: List<String>
)