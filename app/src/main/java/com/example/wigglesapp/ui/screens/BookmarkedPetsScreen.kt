package com.example.wigglesapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.wigglesapp.models.Pet
import com.example.wigglesapp.R
import com.example.wigglesapp.viewmodels.SharedViewModel
import com.example.wigglesapp.data.entity.BookmarkedPet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkedPetsScreen(navController: NavController, sharedViewModel: SharedViewModel){
    // Collect the list of bookmarked pets from the shared ViewModel
    val bookmarkedPets by sharedViewModel.bookmarkedPets.collectAsState()

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text(text = "Favorite Fur-iends") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "Back")
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
            // Column layout to display the list of bookmarked pets or a message if there are none
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (bookmarkedPets.isEmpty()) {
                    // Display message if no pets are bookmarked
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No Favorite Fur-iends Available",
                            fontSize = 20.sp,
                            color = Color(0xFF1a1a73)
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.height(15.dp))
                    // LazyColumn to display the list of bookmarked pets
                    Text(
                        text = "Bookmarked Pets: ",
                        fontSize = 20.sp,
                        color = Color(0xFF1a1a73),
                        textAlign = TextAlign.Center
                    )

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .padding(16.dp)
                    ) {
                        items(bookmarkedPets) { pet ->
                            BookmarkedPetCard(navController = navController, pet = pet.toPet())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BookmarkedPetCard(navController: NavController, pet: Pet){
    // Column layout to display pet details in a card
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate("pet_detail/${pet.id}") }
    ){
        // Display the pet image
        Image(
            painter = rememberAsyncImagePainter(model = pet.imageUrl),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Display the pet's name and breed
        Text(text = pet.name, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
        Text(text = pet.breed, fontSize = 14.sp, color = Color(0xFF5d4037))
    }
}

// Extension function to convert a BookmarkedPet to a Pet
fun BookmarkedPet.toPet(): Pet {
    return Pet(
        id = this.id,
        name = this.name,
        breed = this.breed,
        imageUrl = this.imageUrl,
        gender = this.gender,
        size = this.size,
        characteristics = this.characteristics,
        about = this.about
    )
}
