package com.example.recipeapp.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.room_database.RecipeDatabase
import com.example.recipeapp.data.room_database.RecipeEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LocalRecipeViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = RecipeDatabase.getDatabase(application).recipeDao

    val savedRecipes: StateFlow<List<RecipeEntity>> = dao.getAllSavedRecipes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun saveRecipe(
        name: String,
        cuisine: String,
        difficulty: String,
        imageUrl: String,
        prepTime: String,
        cookTime: String,
        servings: String,
        calories: String,
        ingredients: List<String>,
        instructions: List<String>,
        mealTypes: List<String>,
        tags: List<String>,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            val entity = RecipeEntity(
                name = name.trim(),
                cuisine = cuisine.trim(),
                difficulty = difficulty,
                imageUrl = imageUrl.trim(),
                prepTimeMinutes = prepTime.toIntOrNull() ?: 0,
                cookTimeMinutes = cookTime.toIntOrNull() ?: 0,
                servings = servings.toIntOrNull() ?: 1,
                caloriesPerServing = calories.toIntOrNull() ?: 0,
                ingredients = ingredients.filter { it.isNotBlank() },
                instructions = instructions.filter { it.isNotBlank() },
                mealTypes = mealTypes.filter { it.isNotBlank() },
                tags = tags.filter { it.isNotBlank() }
            )
            dao.insertRecipe(entity)
            onSuccess()
        }
    }
}