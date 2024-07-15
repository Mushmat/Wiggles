package com.example.wigglesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailScreen(navController: NavController, petId: Int, sharedViewModel: SharedViewModel){
    val pet = dummyPets.firstOrNull{ it.id == petId} ?: return
    val bookmarkedPets by sharedViewModel.bookmarkedPets.collectAsState()

    val isBookmarked = bookmarkedPets.any { it.id == pet.id }

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
    ) {
        TopAppBar(
            title = { Text(text = "Paw Details") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Image(
                    painter = rememberAsyncImagePainter(model = pet.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.5f)
                        .padding(bottom = 16.dp)
                )
            }

            item {

                Text(text = pet.name, fontSize = 32.sp, color = Color.Black, style = MaterialTheme.typography.labelLarge)
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Breed: ${pet.breed}", fontSize = 20.sp, color = Color(0xFF5d4037))
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Gender: ${pet.gender}", fontSize = 20.sp, color = Color(0xFF5d4037))
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Size: ${pet.size}", fontSize = 20.sp, color = Color(0xFF5d4037))
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Characteristics: ${pet.characteristics}", fontSize = 18.sp, color = Color(0xFF5d4037))
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "About: ${pet.about}", fontSize = 18.sp, color = Color(0xFF5d4037))
                Spacer(modifier = Modifier.height(16.dp))
    
                Button(onClick = { 
                if(isBookmarked){
                    sharedViewModel.removeBookmark(pet.toBookmarkedPet())
                }else{
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
                    Text(text = if(isBookmarked) "Remove Bookmark" else "Bookmark")
                }
                Spacer(modifier = Modifier.height(16.dp))
                
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
                    Text(text = "Adopt ${pet.name}")
                }
            }
        }
    }}
}

fun Pet.toBookmarkedPet(): BookmarkedPet {
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