package com.example.recipeapp.presentation.navigation


import kotlinx.serialization.Serializable

// Type-safe navigation routes (needs the kotlinx-serialization plugin)
@Serializable
object HomeRoute

// A class (not object) because this screen needs data: which recipe to show
@Serializable
data class RecipeDetailRoute(val recipeId: Int)

@Serializable
object AddRecipeRoute

@Serializable
object MyRecipesRoute