package com.example.wigglesapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wigglesapp.R
import com.example.wigglesapp.viewmodels.SharedViewModel
import com.example.wigglesapp.models.UserPreferences
import com.example.wigglesapp.utils.suggestPets
import com.example.wigglesapp.ui.components.GradientButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetQuizScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val timeOptions = listOf("Less than 1 hour", "1-2 hours", "2-4 hours", "More than 4 hours")
    val sizeOptions = listOf("Small", "Medium", "No preference")
    val childrenOptions = listOf(
        "Yes, under 5 years old",
        "Yes, 5-10 years old",
        "Yes, older than 10 years old",
        "No children"
    )
    val activityOptions = listOf(
        "Very active (exercise daily)",
        "Moderately active (exercise a few times a week)",
        "Not very active (occasional exercise)",
        "Sedentary (rarely exercise)"
    )
    val petOptions = listOf("Yes, dogs", "Yes, cats", "Yes, both dogs and cats", "No other pets")
    val livingOptions =
        listOf("Apartment", "House with a small yard", "House with a large yard", "Farm/Rural area")
    val reasonOptions =
        listOf("Companionship", "Protection", "For children", "Therapy/Emotional support")
    val groomingOptions =
        listOf("Low maintenance", "Moderate maintenance", "High maintenance", "No preference")

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
                title = { Text(text = "Find Best Paw") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
                    .padding(16.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFFFFE0B2), Color(0xFFFFCCBC))
                        )
                    )
                    .padding(16.dp)
            ) {
                //QUESTION 1
                Text(text = "How much time can you dedicate to your pet daily?", fontSize = 20.sp, color = Color.DarkGray)
                timeOptions.forEach { option ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedTime == option,
                            onClick = { selectedTime = option },
                            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFFFF7043))
                        )
                        Text(text = option, fontSize = 16.sp, color = Color.DarkGray)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                //QUESTION 2
                Text(text = "What size of pet are you looking for?", fontSize = 20.sp, color = Color.DarkGray)
                sizeOptions.forEach { option ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedSize == option,
                            onClick = { selectedSize = option },
                            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFFFF7043))
                        )
                        Text(text = option, fontSize = 16.sp, color = Color.DarkGray)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                //QUESTION 3
                Text(text = "Do you have children at home?", fontSize = 20.sp, color = Color.DarkGray)
                childrenOptions.forEach { option ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedChildren == option,
                            onClick = { selectedChildren = option },
                            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFFFF7043))
                        )
                        Text(text = option, fontSize = 16.sp, color = Color.DarkGray)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                //QUESTION 4
                Text(text = "How active is your lifestyle?", fontSize = 20.sp, color = Color.DarkGray)
                activityOptions.forEach { option ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedActivity == option,
                            onClick = { selectedActivity = option },
                            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFFFF7043))
                        )
                        Text(text = option, fontSize = 16.sp, color = Color.DarkGray)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                //QUESTION 5
                Text(text = "Do you have other pets at home?", fontSize = 20.sp, color = Color.DarkGray)
                petOptions.forEach { option ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedPets == option,
                            onClick = { selectedPets = option },
                            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFFFF7043))
                        )
                        Text(text = option, fontSize = 16.sp, color = Color.DarkGray)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                //QUESTION 6
                Text(text = "What type of living environment do you have?", fontSize = 20.sp, color = Color.DarkGray)
                livingOptions.forEach { option ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedLiving == option,
                            onClick = { selectedLiving = option },
                            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFFFF7043))
                        )
                        Text(text = option, fontSize = 16.sp, color = Color.DarkGray)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                //QUESTION 7
                Text(text = "What is your main reason for getting a pet?", fontSize = 20.sp, color = Color.DarkGray)
                reasonOptions.forEach { option ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedReason == option,
                            onClick = { selectedReason = option },
                            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFFFF7043))
                        )
                        Text(text = option, fontSize = 16.sp, color = Color.DarkGray)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                //QUESTION 8
                Text(text = "Do you have any preference for grooming needs?", fontSize = 20.sp, color = Color.DarkGray)
                groomingOptions.forEach { option ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedGrooming == option,
                            onClick = { selectedGrooming = option },
                            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFFFF7043))
                        )
                        Text(text = option, fontSize = 16.sp, color = Color.DarkGray)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                GradientButton(
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
                    text = "Find Best Pet",
                    gradient = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFFFA726), Color(0xFFFF7043))
                    )
                )
            }
        }
    }
}

