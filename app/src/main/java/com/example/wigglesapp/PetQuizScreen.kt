package com.example.wigglesapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
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

    var selectedTime by remember { mutableStateOf("") }
    var selectedSize by remember { mutableStateOf("") }
    var selectedChildren by remember { mutableStateOf("") }
    var selectedActivity by remember { mutableStateOf("") }
    var selectedPets by remember { mutableStateOf("") }
    var selectedLiving by remember { mutableStateOf("") }
    var selectedReason by remember { mutableStateOf("") }
    var selectedGrooming by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Find Best Pet") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // QUESTION 1
            Text(text = "How much time can you dedicate to your pet daily?", fontSize = 18.sp)
            timeOptions.forEach { option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedTime == option,
                        onClick = { selectedTime = option }
                    )
                    Text(text = option)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // QUESTION 2
            Text(text = "What size of pet are you looking for?", fontSize = 18.sp)
            sizeOptions.forEach { option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedSize == option,
                        onClick = { selectedSize = option }
                    )
                    Text(text = option)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // QUESTION 3
            Text(text = "Do you have children at home?", fontSize = 18.sp)
            childrenOptions.forEach { option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedChildren == option,
                        onClick = { selectedChildren = option }
                    )
                    Text(text = option)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // QUESTION 4
            Text(text = "How active is your lifestyle?", fontSize = 18.sp)
            activityOptions.forEach { option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedActivity == option,
                        onClick = { selectedActivity = option }
                    )
                    Text(text = option)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // QUESTION 5
            Text(text = "Do you have other pets at home?", fontSize = 18.sp)
            petOptions.forEach { option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedPets == option,
                        onClick = { selectedPets = option }
                    )
                    Text(text = option)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // QUESTION 6
            Text(text = "What type of living environment do you have?", fontSize = 18.sp)
            livingOptions.forEach { option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedLiving == option,
                        onClick = { selectedLiving = option }
                    )
                    Text(text = option)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // QUESTION 7
            Text(text = "What is your main reason for getting a pet?", fontSize = 18.sp)
            reasonOptions.forEach { option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedReason == option,
                        onClick = { selectedReason = option }
                    )
                    Text(text = option)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // QUESTION 8
            Text(text = "Do you have any preference for grooming needs?", fontSize = 18.sp)
            groomingOptions.forEach { option ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedGrooming == option,
                        onClick = { selectedGrooming = option }
                    )
                    Text(text = option)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val preferences = UserPreferences(
                        timeDedication = selectedTime,
                        sizePreference = selectedSize,
                        childrenAtHome = selectedChildren,
                        activityLevel = selectedActivity,
                        otherPets = selectedPets,
                        livingEnvironment = selectedLiving,
                        reasonForPet = selectedReason,
                        groomingPreference = selectedGrooming
                    )
                    sharedViewModel.setSuggestedPets(suggestPets(preferences))
                    navController.navigate("suggested_pets_screen")
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Find Best Pet")
            }
        }
    }
}
