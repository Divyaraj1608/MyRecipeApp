package com.example.recipeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.recipeapp.presentation.screens.AddRecipe.AddRecipeScreen
import com.example.recipeapp.presentation.screens.MyRecipes.SavedRecipesScreen
import com.example.recipeapp.presentation.screens.RecipeDetail.RecipeDetailScreen
import com.example.recipeapp.presentation.screens.home.HomeScreen
import com.example.recipeapp.presentation.viewmodels.LocalRecipeViewModel

@Composable
fun RecipeNavHost() {
    val navController = rememberNavController()
    // Shared ViewModel instance for Room database operations across local screens
    val localRecipeViewModel: LocalRecipeViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        composable<HomeRoute> {
            HomeScreen(
                onRecipeClick = { id ->
                    navController.navigate(RecipeDetailRoute(recipeId = id))
                },
                onFabClick = {
                    navController.navigate(AddRecipeRoute)
                },
                onNavigateToSaved = {
                    navController.navigate(MyRecipesRoute) {
                        popUpTo(HomeRoute) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable<RecipeDetailRoute> { backStackEntry ->
            val detailRoute = backStackEntry.toRoute<RecipeDetailRoute>()
            RecipeDetailScreen(
                recipeId = detailRoute.recipeId,
                onBack = { navController.popBackStack() }
            )
        }

        composable<AddRecipeRoute> {
            AddRecipeScreen(
                viewModel = localRecipeViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable<MyRecipesRoute> {
            SavedRecipesScreen(
                viewModel = localRecipeViewModel,
                onRecipeClick = { id ->
                    navController.navigate(RecipeDetailRoute(recipeId = id.toInt()))
                },
                onNavigateToHome = {
                    navController.navigate(HomeRoute) {
                        popUpTo(HomeRoute) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}