package com.example.wigglesapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wigglesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(navController: NavController, applyFilters: (List<String>, List<String>, List<String>) -> Unit) {

    // State variables to keep track of selected filters

    val selectedBreeds = remember { mutableStateListOf<String>() }
    val selectedGenders = remember { mutableStateListOf<String>() }
    val selectedSizes = remember { mutableStateListOf<String>() }

    // Lists of available breeds, genders, and sizes

    val breeds = listOf(
        "Chihuahua",
        "Beagle",
        "Pit Bull Terrier",
        "Dachshund",
        "Plott Hound",
        "Hound",
        "Mixed Breed",
        "Labrador Retriever",
        "Jack Russell Terrier",
        "Australian Cattle Dog",
        "German Shepherd",
        "Domestic Short Hair",
        "American Short Hair",
        "Domestic Medium Hair",
        "Abyssinian",
        "Bengal",
        "British Short Hair",
        "Dilute Calico",
        "Russian Blue"
    )
    val genders = listOf("Male", "Female")
    val sizes = listOf("Small", "Medium")

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Filter Pets") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Go Back"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            // Main content container
            Box(modifier = Modifier.fillMaxSize()) {
                // Background image
                Image(
                    painter = painterResource(id = R.drawable.bg),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            LazyColumn(
                // LazyColumn to display filter options
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                item {
                    // Breed selection
                    Text(text = "Select Breed")
                    breeds.forEach { breed ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = selectedBreeds.contains(breed),
                                onCheckedChange = { checked ->
                                    if (checked) {
                                        selectedBreeds.add(breed)
                                    } else {
                                        selectedBreeds.remove(breed)
                                    }
                                }
                            )
                            Text(text = breed)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    // Gender selection
                    Text(text = "Select Gender")
                    genders.forEach { gender ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = selectedGenders.contains(gender),
                                onCheckedChange = { checked ->
                                    if (checked) {
                                        selectedGenders.add(gender)
                                    } else {
                                        selectedGenders.remove(gender)
                                    }
                                }
                            )
                            Text(text = gender)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    // Size selection

                    Text(text = "Select Size")
                    sizes.forEach { size ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = selectedSizes.contains(size),
                                onCheckedChange = { checked ->
                                    if (checked) {
                                        selectedSizes.add(size)
                                    } else {
                                        selectedSizes.remove(size)
                                    }
                                }
                            )
                            Text(text = size)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Apply filters button
                    Button(onClick = {
                        applyFilters(selectedBreeds, selectedGenders, selectedSizes)
                        navController.popBackStack()
                    }) {
                        Text(text = "Apply Filters")
                    }
                }
            }
        }
    }
}