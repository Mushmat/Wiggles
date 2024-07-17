package com.example.wigglesapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
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
import com.example.wigglesapp.data.entity.BookmarkedPet
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailScreen(navController: NavController, petId: Int, sharedViewModel: SharedViewModel) {
    // Find the pet with the given ID
    val pet = dummyPets.firstOrNull { it.id == petId } ?: return
    // Get the list of bookmarked pets from the shared view model
    val bookmarkedPets by sharedViewModel.bookmarkedPets.collectAsState()
    // Check if the current pet is bookmarked
    val isBookmarked = bookmarkedPets.any { it.id == pet.id }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            // Background image
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Top App Bar with a back button
            TopAppBar(
                title = { Text(text = "Paw Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )

            // LazyColumn to display the pet details
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    // Pet image
                    Image(
                        painter = rememberAsyncImagePainter(model = pet.imageUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1.5f)
                            .padding(bottom = 16.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .shadow(8.dp, RoundedCornerShape(16.dp))
                    )
                }

                item {
                    // Display pet details
                    Text(
                        text = pet.name,
                        fontSize = 32.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    Text(
                        text = "Breed: ${pet.breed}",
                        fontSize = 20.sp,
                        color = Color(0xFF5d4037),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    Text(
                        text = "Gender: ${pet.gender}",
                        fontSize = 20.sp,
                        color = Color(0xFF5d4037),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    Text(
                        text = "Size: ${pet.size}",
                        fontSize = 20.sp,
                        color = Color(0xFF5d4037),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    Text(
                        text = "Characteristics: ${pet.characteristics}",
                        fontSize = 18.sp,
                        color = Color(0xFF5d4037),
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    Text(
                        text = "About: ${pet.about}",
                        fontSize = 18.sp,
                        color = Color(0xFF5d4037),
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Button to bookmark or remove bookmark
                    Button(
                        onClick = {
                            if (isBookmarked) {
                                sharedViewModel.removeBookmark(pet.toBookmarkedPet())
                            } else {
                                sharedViewModel.bookmarkPet(pet.toBookmarkedPet())
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(Color(0xFFFFA726), Color(0xFFFF7043))
                                )
                            )
                            .shadow(8.dp, RoundedCornerShape(12.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Text(
                            text = if (isBookmarked) "Remove Bookmark" else "Bookmark",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Button to navigate to the adoption application screen
                    Button(
                        onClick = { navController.navigate("adoption_application/${pet.id}") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(Color(0xFFFFA726), Color(0xFFFF7043))
                                )
                            )
                            .shadow(8.dp, RoundedCornerShape(12.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Text(
                            text = "Adopt ${pet.name}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

// Extension function to convert a Pet to a BookmarkedPet
fun Pet.toBookmarkedPet(): BookmarkedPet {
    // Get the current user ID from FirebaseAuth
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: throw IllegalStateException("User not logged in")
    return BookmarkedPet(
        id = this.id,
        userId = userId,
        name = this.name,
        breed = this.breed,
        imageUrl = this.imageUrl,
        gender = this.gender,
        size = this.size,
        characteristics = this.characteristics,
        about = this.about
    )
}
