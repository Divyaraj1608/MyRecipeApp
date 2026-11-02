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

class RecipeDetailViewModel: ViewModel() {

    private val repository: RecipeRepository = RecipeRepositoryImpl(
        apiService = RecipeApiService(client = KtorClient.client)
    )

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var recipe by mutableStateOf<RecipeDto?>(null)
        private set

    fun fetchRecipeDetails(id: Int) {
        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            try {
                recipe = repository.getRecipeById(id)
            } catch (e: Exception) {
                errorMessage = e.message ?: "An unexpected error occurred"
            } finally {
                isLoading = false
            }
        }
    }
}