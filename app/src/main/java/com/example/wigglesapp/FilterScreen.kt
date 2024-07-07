package com.example.wigglesapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

@Composable
fun FilterScreen(navController: NavController, applyFilters: (String, String, String) -> Unit) {
    var selectedBreed by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("") }
    var selectedSize by remember { mutableStateOf("") }

    val breeds = listOf("Pit Bull Terrier", "Dachshund", "Plott Hound", "Hound", "Mixed Breed", "Labrador Retriever", "Jack Russell Terrier", "Australian Cattle Dog", "German Shepherd", "Domestic Short Hair", "American Short Hair", "Domestic Medium Hair", "Abyssinian", "Bengal", "British Short Hair", "Dilute Calico")
    val genders = listOf("Male", "Female")
    val sizes = listOf("Small", "Medium")

}