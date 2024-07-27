package com.example.wigglesapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.wigglesapp.R
import com.example.wigglesapp.viewmodels.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdoptionApplicationScreen(navController: NavController, petId: Int, sharedViewModel: SharedViewModel) {
    // Retrieve the pet details based on the petId
    val pet = dummyPets.firstOrNull { it.id == petId } ?: return

    // List of questions to be answered in the adoption application
    val questions = listOf(
        "Who will be the owner of the pet?",
        "What is their gender?",
        "What is their age?",
        "What is their email?",
        "Do they need any assistive services along with the pet?",
        "Does their colony allow pets?",
        "Have they had pets before? If yes, what kind?",
        "How much time can they dedicate to the pet?",
        "Do they travel frequently? If yes, where will the pet be during that phase?",
        "How long can they hold the adoption request?"
    )

    // State to hold answers to the questions
    val answers = remember { mutableStateOf(List(questions.size) { "" }) }

    // State to track the current question being answered
    var currentQuestion by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Go for the PAW!!!") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        // Main content container
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            // Background image
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            // Column layout to display pet details and questions
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Display the chosen pet image
                Image(
                    painter = rememberAsyncImagePainter(model = pet.imageUrl),
                    contentDescription = "Pet Chosen for Adoption",
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(16.dp)
                )
                Text(text = "Fur-ever Friendly", fontSize = 24.sp, color = Color(0xff1a1a73))
                Spacer(modifier = Modifier.height(16.dp))

                // Display the pet's name and breed
                Text(
                    text = "Paw-sonal Name: ${pet.name}",
                    fontSize = 20.sp,
                    color = Color(0xFF800000)
                )
                Text(
                    text = "Paw-sonal Breed: ${pet.breed}",
                    fontSize = 20.sp,
                    color = Color(0xFF800000)
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Card to display the current question and input field
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Display the current question
                        Text(
                            text = questions[currentQuestion],
                            fontSize = 18.sp,
                            color = Color(0xFF1a1a73)
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        // Input field to enter the answer
                        OutlinedTextField(
                            value = answers.value[currentQuestion],
                            onValueChange = { newValue ->
                                answers.value = answers.value.toMutableList()
                                    .also { it[currentQuestion] = newValue }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        // Row layout to hold navigation buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Back button to navigate to the previous question
                            if (currentQuestion > 0) {
                                Button(onClick = { currentQuestion-- }) {
                                    Text(text = "Back")
                                }
                            }
                            // Next button to navigate to the next question or submit the application
                            if (currentQuestion < questions.size - 1) {
                                Button(onClick = { currentQuestion++ }) {
                                    Text(text = "Next")
                                }
                            } else {
                                // Submit the adoption application
                                Button(onClick = {
                                    sharedViewModel.submitAdoptionApplication(petId, answers.value)
                                    navController.navigate("adoption_success")
                                }) {
                                    Text(text = "Submit")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
