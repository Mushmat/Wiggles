package com.example.wigglesapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PetQuizScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val timeOptions = listOf("Less than 1 hour", "1-2 hours", "2-4 hours", "More than 4 hours")
    val sizeOptions = listOf("Small", "Medium", "Large", "No preference")
    val childrenOptions = listOf("Yes, under 5 years old", "Yes, 5-10 years old", "Yes, older than 10 years old", "No children")
    val activityOptions = listOf("Very active (exercise daily)", "Moderately active (exercise a few times a week)", "Not very active (occasional exercise)", "Sedentary (rarely exercise)")
    val petOptions = listOf("Yes, dogs", "Yes, cats", "Yes, both dogs and cats", "No other pets")
    val livingOptions = listOf("Apartment", "House with a small yard", "House with a large yard", "Farm/Rural area")
    val reasonOptions = listOf("Companionship", "Protection", "For children", "Therapy/Emotional support")
    val groomingOptions = listOf("Low maintenance", "Moderate maintenance", "High maintenance", "No preference")

}