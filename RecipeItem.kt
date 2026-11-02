package com.example.recipeapp.data.room_database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val cuisine: String,
    val difficulty: String,
    val imageUrl: String,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val servings: Int,
    val caloriesPerServing: Int,
    val ingredients: List<String>,
    val instructions: List<String>,
    val mealTypes: List<String>,
    val tags: List<String>
)