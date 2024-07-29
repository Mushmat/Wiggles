package com.example.wigglesapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.wigglesapp.models.Pet
import com.example.wigglesapp.R
import com.example.wigglesapp.viewmodels.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestedPetsScreen(navController: NavController, sharedViewModel: SharedViewModel){
    // Collect the suggested pets from the shared view model
    val suggestedPets by sharedViewModel.suggestedPets.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            // Top AppBar with title and back navigation
            TopAppBar(
                title = { Text(text = "Suggested Paws") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->

        Box(modifier = Modifier.fillMaxSize()) {
            // Background image
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (suggestedPets.isEmpty()) {
                // Display message if no suggested pets are found
                Text(
                    text = "No pets found matching your preference!",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                // Display the list of suggested pets
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    suggestedPets.forEach { pet ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            SuggestedPetCard(navController = navController, pet = pet)
                        }
                    }
                }
            }
        }}
    }
}

@Composable
fun SuggestedPetCard(navController: NavController, pet: Pet){
    // Card to display suggested pet details
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate("pet_detail/${pet.id}") }
    ){
        Image(
            // Display pet image
            painter = rememberAsyncImagePainter(model = pet.imageUrl),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Display pet name and breed
        Text(text = pet.name, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
        Text(text = pet.breed, fontSize = 14.sp, color = Color(0xFF5d4037))

        Text(text = "Click on the pet to adopt it!", fontSize = 16.sp, color = Color(0xFF5d4037))
    }
}