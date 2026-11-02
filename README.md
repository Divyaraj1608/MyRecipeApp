
# 🍽️ MyRecipeApp

A modern, declarative Android application designed for culinary enthusiasts to discover, explore, and organize recipes[cite: 1, 3, 6]. Built completely with **Jetpack Compose**, **Clean Architecture + MVVM**, **Ktor Client** for remote API networking, and **Room Database** for offline local recipe storage[cite: 1, 2].

---

## ✨ Features

- **Explore & Filter Recipes:** Browse a curated catalog of recipes categorized by cuisine (American, Asian, Italian, Brazilian, etc.)[cite: 3].
- **Detailed Recipe Overview:** View comprehensive cooking breakdowns including preparation time, cooking time, serving count, calorie estimates, and step-by-step ingredient lists[cite: 4].
- **Custom Recipe Creator:** Form to input custom recipes with title, cuisine, difficulty tags (Easy/Medium/Hard), cook times, servings, and dynamic ingredient inputs[cite: 5].
- **Offline / Saved Recipes:** Save and manage personal recipes locally using an integrated Room database[cite: 2, 6].
- **Clean Modern UI:** Intuitive layout with custom Material 3 components, bottom navigation, smooth state handling, and dedicated loading/error states[cite: 2, 3].

---

## 🛠️ Tech Stack & Architecture

- **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose) (100% declarative UI)[cite: 1]
- **Language:** Kotlin[cite: 2]
- **Architecture:** Clean Architecture + MVVM (Model-View-ViewModel)[cite: 1, 2]
- **Networking:** [Ktor Client](https://ktor.io/) for asynchronous HTTP networking and JSON serialization[cite: 1, 2]
- **Local Persistence:** [Room Database](https://developer.android.com/training/data-storage/room) with Type Converters for structured local caching[cite: 1, 2]
- **Navigation:** Navigation Compose (`RecipeNavHost`) with type-safe route management[cite: 2]
- **Asynchronous Operations:** Kotlin Coroutines & `StateFlow`[cite: 2]
- **Build System:** Gradle Kotlin DSL (`build.gradle.kts`) with Version Catalogs (`libs.versions.toml`)[cite: 2]


---

## 📸 Screenshots

| Home Screen | Recipe Details | Add Recipe | Saved Recipes |
|:---:|:---:|:---:|:---:|
| <img width="297" height="652" alt="Screenshot 2026-11-02 163316" src="https://github.com/user-attachments/assets/57b008c4-d557-4d36-9936-3165c10367c3" /> |
 |<img width="297" height="662" alt="Screenshot 2026-11-02 163342" src="https://github.com/user-attachments/assets/d87df132-ff1a-40df-a349-121610e4cece" />|
 |<img width="293" height="665" alt="Screenshot 2026-11-02 163602" src="https://github.com/user-attachments/assets/8bd0efcc-599c-430f-8b63-e0baddf62501" />|
 |<img width="297" height="647" alt="Screenshot 2026-11-02 163637" src="https://github.com/user-attachments/assets/e111fe54-fa5e-4d50-822c-b957b8c8880b" /> |
 


---

## 📂 Project Structure

```text
com.example.recipeapp
├── data
│   ├── remote
│   │   ├── dto              # Data transfer objects (Requests/Responses)
│   │   ├── KtorClient.kt    # Ktor HTTP client configuration
│   │   └── RecipeApiService # Remote API interface
│   ├── repository           # Repository implementations
│   └── room_database        # Room entities, DAOs, converters, & database
├── domain
│   └── repository           # Repository interface contracts
├── presentation
│   ├── components           # Reusable UI elements (App bars, loaders, errors)
│   ├── navigation           # Navigation Host and route definitions
│   ├── screens
│   │   ├── home             # Category list, headers, & recipe feed
│   │   ├── RecipeDetail     # Nutritional chips, stats, and ingredients
│   │   ├── AddRecipe        # Custom recipe input forms
│   │   └── MyRecipes        # Local/saved recipe collection
│   └── viewmodels           # Screen-level state management ViewModels
└── ui.theme                 # Color schemes, typography, and styling themes
