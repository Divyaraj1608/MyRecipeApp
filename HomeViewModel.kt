package com.example.recipeapp.presentation.viewmodels


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.data.remote.KtorClient
import com.example.recipeapp.data.remote.RecipeApiService
import com.example.recipeapp.data.remote.dto.RecipeDto
import com.example.recipeapp.data.repository.RecipeRepositoryImpl
import com.example.recipeapp.domain.repository.RecipeRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository: RecipeRepository = RecipeRepositoryImpl(
        apiService = RecipeApiService(client = KtorClient.client)
    )

    // UI state
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Recipes currently shown (after category filter)
    var recipes by mutableStateOf<List<RecipeDto>>(emptyList())
        private set

    var selectedCategory by mutableStateOf("All")
        private set

    var categories by mutableStateOf(listOf("All"))
        private set

    // Every recipe from the server, never filtered
    private var allRecipes: List<RecipeDto> = emptyList()

    // Runs automatically when the ViewModel is created
    init {
        fetchRecipes()
    }

    fun fetchRecipes() {
        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            try {
                val result = repository.getAllRecipes()
                allRecipes = result

                // Categories = distinct cuisines, sorted, with "All" first
                val cuisines = result.map { it.cuisine }.distinct().sorted()
                categories = listOf("All") + cuisines

                applyFilters()
            } catch (e: Exception) {
                errorMessage = e.message ?: "An unexpected error occurred"
            } finally {
                isLoading = false
            }
        }
    }

    private fun applyFilters() {
        recipes = if (selectedCategory == "All") {
            allRecipes
        } else {
            allRecipes.filter { it.cuisine == selectedCategory }
        }
    }

    fun onCategorySelected(category: String) {
        selectedCategory = category
        applyFilters()
    }
}
