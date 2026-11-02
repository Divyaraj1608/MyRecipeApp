package com.example.recipeapp.data.remote

import com.example.recipeapp.data.remote.dto.AddRecipeRequest
import com.example.recipeapp.data.remote.dto.AddRecipeResponse
import com.example.recipeapp.data.remote.dto.RecipeDto
import com.example.recipeapp.data.remote.dto.RecipesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RecipeApiService(private val client: HttpClient) {

    // GET https://dummyjson.com/recipes
    suspend fun getAllRecipes(): RecipesResponse {
        return client.get("${KtorClient.BASE_URL}recipes").body()
    }

    // To Get recipe through ID
    suspend fun getRecipeById(id: Int): RecipeDto {
        return client.get("${KtorClient.BASE_URL}recipes/$id").body()
    }

    // To send data(recipe) to the server
    // POST https://dummyjson.com/recipes/add
    // (dummyjson fakes this; nothing is really saved)
    suspend fun addRecipe(request: AddRecipeRequest): AddRecipeResponse {
        return client.post("${KtorClient.BASE_URL}recipes/add") {
            contentType(ContentType.Application.Json)
            setBody(request) // Kotlin object -> JSON
        }.body()
    }
}



