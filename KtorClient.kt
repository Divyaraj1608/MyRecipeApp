package com.example.recipeapp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {

    const val BASE_URL = "https://dummyjson.com/"
    // For EndPoint
    //const val RECIPE = "$BASE_URL/Recipes"
    val client = HttpClient(Android) {
        // Converts JSON <-> Kotlin objects automatically
        install(ContentNegotiation) {
            json(
                Json {
                    // Don't crash if the server
                    // sends keys our DTO doesn't have
                    ignoreUnknownKeys = true
                }
            )
        }
    }
}