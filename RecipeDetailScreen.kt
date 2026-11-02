package com.example.recipeapp.presentation.screens.RecipeDetail


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipeapp.presentation.components.ErrorMessage
import com.example.recipeapp.presentation.components.LoadingIndicator
import com.example.recipeapp.presentation.components.MyTopAppBar
import com.example.recipeapp.presentation.viewmodels.RecipeDetailViewModel
import com.example.recipeapp.ui.theme.MyOrange

@Composable
fun RecipeDetailScreen(
    recipeId: Int,
    onBack: () -> Unit,
    viewModel: RecipeDetailViewModel = viewModel()
) {
    // Fetch when the screen opens (and again if recipeId changes)
    LaunchedEffect(recipeId) {
        viewModel.fetchRecipeDetails(recipeId)
    }

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "Recipe Details",
                onBackClick = onBack,
                icon = Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MyOrange.copy(alpha = 0.02f))
                .padding(innerPadding)
        ) {
            val recipe = viewModel.recipe

            when {
                viewModel.isLoading -> {
                    LoadingIndicator(strokeWidth = 1.dp)
                }

                viewModel.errorMessage != null -> {
                    ErrorMessage(
                        errorMessage = viewModel.errorMessage,
                        // Retry here must re-fetch THIS recipe, so it needs the id
                        onRetry = { viewModel.fetchRecipeDetails(recipeId) }
                    )
                }

                recipe != null -> {
                    RecipeDetailContent(details = recipe)
                }
            }
        }
    }
}