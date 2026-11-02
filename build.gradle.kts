plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)

    // serialization plugin
    id("org.jetbrains.kotlin.plugin.serialization")
    //KSP plugin
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.example.recipeapp"
    compileSdk {
        version = release(37)
    }


    defaultConfig {
        applicationId = "com.example.recipeapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)


    // For Ktor (HTTP client)
    implementation("io.ktor:ktor-client-core:3.5.0")
    implementation("io.ktor:ktor-client-android:3.5.0")
    implementation("io.ktor:ktor-client-content-negotiation:3.5.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.5.0")

    // For Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

    // For Navigation
    implementation("androidx.navigation:navigation-compose:2.9.0")

    // For viewModel Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.0")

    // For coil (Url to Images)
    implementation("io.coil-kt:coil-compose:2.7.0")

    // Extra icons (RestaurantMenu, EggAlt, Whatshot ...)
    implementation("androidx.compose.material:material-icons-extended")

    // Room dependency
    implementation("androidx.room:room-runtime:2.8.4")

    // KSP dependency
    ksp("androidx.room:room-compiler:2.8.4")

    // Co-routines dependency
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

}




