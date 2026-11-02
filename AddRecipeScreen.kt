package com.example.recipeapp.presentation.screens.AddRecipe

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipeapp.presentation.viewmodels.LocalRecipeViewModel

private val AccentOrange = Color(0xFFFF5722)
private val LightOrangeBg = Color(0xFFFFF0EB)
private val BorderGray = Color(0xFFD6D6D6)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeScreen(
    viewModel: LocalRecipeViewModel,
    onNavigateBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var cuisine by remember { mutableStateOf("") }
    var difficulty by remember { mutableStateOf("Easy") }
    var imageUrl by remember { mutableStateOf("") }
    var prepTime by remember { mutableStateOf("") }
    var cookTime by remember { mutableStateOf("") }
    var servings by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }

    val ingredients = remember { mutableStateListOf("") }
    val instructions = remember { mutableStateListOf("") }
    val mealTypes = remember { mutableStateListOf("") }
    val tags = remember { mutableStateListOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Recipe", fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    viewModel.saveRecipe(
                        name = name,
                        cuisine = cuisine,
                        difficulty = difficulty,
                        imageUrl = imageUrl,
                        prepTime = prepTime,
                        cookTime = cookTime,
                        servings = servings,
                        calories = calories,
                        ingredients = ingredients,
                        instructions = instructions,
                        mealTypes = mealTypes,
                        tags = tags,
                        onSuccess = onNavigateBack
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AccentOrange),
                shape = RoundedCornerShape(26.dp)
            ) {
                Text("Save Recipe", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        },
        containerColor = Color.White
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                LabeledInputField(
                    label = "Recipe Name",
                    value = name,
                    placeholder = "e.g. Spicy Pasta",
                    onValueChange = { name = it }
                )
            }

            item {
                LabeledInputField(
                    label = "Cuisine",
                    value = cuisine,
                    placeholder = "e.g. Italian",
                    onValueChange = { cuisine = it }
                )
            }

            item {
                Text("Difficulty", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("Easy", "Medium", "Hard").forEach { level ->
                        val selected = difficulty == level
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = if (selected) AccentOrange else Color.Transparent,
                            border = BorderStroke(1.dp, if (selected) AccentOrange else BorderGray),
                            modifier = Modifier.clickable { difficulty = level }
                        ) {
                            Text(
                                text = level,
                                color = if (selected) Color.White else Color.Gray,
                                modifier = Modifier.padding(horizontal = 18.dp, vertical = 6.dp),
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }

            item {
                LabeledInputField(
                    label = "Image URL",
                    value = imageUrl,
                    placeholder = "https://...",
                    onValueChange = { imageUrl = it }
                )
            }

            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    LabeledInputField(
                        label = "Prep (min)",
                        value = prepTime,
                        placeholder = "15",
                        onValueChange = { prepTime = it },
                        modifier = Modifier.weight(1f)
                    )
                    LabeledInputField(
                        label = "Cook (min)",
                        value = cookTime,
                        placeholder = "30",
                        onValueChange = { cookTime = it },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    LabeledInputField(
                        label = "Servings",
                        value = servings,
                        placeholder = "4",
                        onValueChange = { servings = it },
                        modifier = Modifier.weight(1f)
                    )
                    LabeledInputField(
                        label = "Calories",
                        value = calories,
                        placeholder = "350",
                        onValueChange = { calories = it },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                DynamicFieldSection(
                    title = "Ingredients",
                    placeholderPrefix = "Ingredient",
                    items = ingredients
                )
            }

            item {
                DynamicFieldSection(
                    title = "Instructions",
                    placeholderPrefix = "Step",
                    items = instructions
                )
            }

            item {
                DynamicFieldSection(
                    title = "Meal Types",
                    placeholderPrefix = "Meal type",
                    items = mealTypes
                )
            }

            item {
                DynamicFieldSection(
                    title = "Tags",
                    placeholderPrefix = "Tag",
                    items = tags
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun LabeledInputField(
    label: String,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color.Gray) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = AccentOrange,
                unfocusedBorderColor = BorderGray
            )
        )
    }
}

@Composable
private fun DynamicFieldSection(
    title: String,
    placeholderPrefix: String,
    items: androidx.compose.runtime.snapshots.SnapshotStateList<String>
) {
    Column {
        Text(text = title, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(6.dp))
        items.forEachIndexed { index, itemValue ->
            OutlinedTextField(
                value = itemValue,
                onValueChange = { items[index] = it },
                placeholder = { Text("$placeholderPrefix ${index + 1}", color = Color.Gray) },
                trailingIcon = {
                    IconButton(onClick = { items.removeAt(index) }) {
                        Icon(Icons.Default.Close, contentDescription = "Remove", tint = Color.Gray)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AccentOrange,
                    unfocusedBorderColor = BorderGray
                )
            )
        }
        Row(
            modifier = Modifier
                .clickable { items.add("") }
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add", tint = AccentOrange)
            Spacer(modifier = Modifier.width(4.dp))
            Text("Add", color = AccentOrange, fontWeight = FontWeight.SemiBold)
        }
    }
}