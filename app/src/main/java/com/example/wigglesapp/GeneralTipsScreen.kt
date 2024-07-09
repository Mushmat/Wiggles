package com.example.wigglesapp

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneralTipsScreen(navController: NavController){
    val tips = listOf(
        "1. REGULAR VETERINARY CHECKUPS: Schedule regular visits to the vet for vaccinations and health checkups.",
        "2. BALANCED DIET: Provide a well-balanced diet suitable for your pet's age, size, and breed.",
        "3. FRESH WATER: Ensure your pet always has access to fresh, clean water.",
        "4. EXERCISE: Give your pet regular exercise to keep them healthy and prevent obesity.",
        "5. GROOMING: Regularly groom your pet to maintain their coat and check for any abnormalities.",
        "6. TRAINING: Invest time in training your pet to follow basic commands and good behavior.",
        "7. SAFE ENVIRONMENT: Create a safe and comfortable living environment for your pet.",
        "8. DENTAL CARE: Maintain your pet’s dental hygiene by brushing their teeth or providing dental treats.",
        "9. PREVENT PARASITES: Use preventive treatments to protect your pet from fleas, ticks, and worms.",
        "10. MENTAL STIMULATION: Provide toys and activities to keep your pet mentally stimulated and engaged."
    )
}