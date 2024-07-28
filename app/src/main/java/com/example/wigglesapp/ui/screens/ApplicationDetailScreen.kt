package com.example.wigglesapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.wigglesapp.R
import com.example.wigglesapp.viewmodels.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationDetailScreen(navController: NavController, petId: Int, sharedViewModel: SharedViewModel) {

    // Retrieve the specific adoption application based on petId
    val application =
        sharedViewModel.adoptionApplications.collectAsState().value.firstOrNull { it.petId == petId }
            ?: return

    // Retrieve the pet details based on petId
    val pet = dummyPets.firstOrNull { it.id == petId } ?: return

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Paw-gress Detail") },
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
        Box(modifier = Modifier.fillMaxSize()) {
            // Background image
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            // LazyColumn to display pet details and application answers
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                item {
                    // Display the pet image
                    Image(
                        painter = rememberAsyncImagePainter(model = pet.imageUrl),
                        contentDescription = "Pet Image",
                        modifier = Modifier
                            .size(128.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // Display the pet's name, breed, and application status
                    Text(text = "Paw-sonal Name: ${pet.name}", fontSize = 20.sp, color = Color(0xFF1a1a73))
                    Text(text = "Paw-sonal Breed: ${pet.breed}", fontSize = 20.sp, color = Color(0xFF1a1a73))
                    Text(text = "STATUS: ${application.status}", fontSize = 20.sp, color = when(application.status) {
                        "ACCEPTED" -> Color(0xFF2e7d32)
                        "DENIED" -> Color(0xFFD32F2F)
                        else -> Color(0xFFFFA000)
                    })
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Display each question and its corresponding answer
                itemsIndexed(application.answers) { index, answer ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            // Display the question label
                            Text(
                                text = "Q${index + 1}: ${getQuestionLabel(index)}",
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            // Display the answer
                            Text(text = answer, fontSize = 18.sp)
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

// Function to get the label for each question based on the index
fun getQuestionLabel(index: Int): String {
    return when (index) {
        0 -> "Name"
        1 -> "Gender"
        2 -> "Age"
        3 -> "Email"
        4 -> "Services needed"
        5 -> "Pet Allowance"
        6 -> "Pet History"
        7 -> "Time Allowance"
        8 -> "Travel Habits"
        9 -> "Request Hold Time"
        else -> ""
    }
}
