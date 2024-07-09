package com.example.wigglesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailScreen(navController: NavController, petId: Int, sharedViewModel: SharedViewModel){
    val pet = dummyPets.firstOrNull{ it.id == petId} ?: return
    val bookmarkedPets by sharedViewModel.bookmarkedPets.collectAsState()

    val isBookmarked = bookmarkedPets.contains(pet)
    
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = { Text(text = "Pet Details") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
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
                    painter = rememberImagePainter(data = pet.imageUrl),
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

                Text(text = "Breed: ${pet.breed}", fontSize = 20.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Gender: ${pet.gender}", fontSize = 20.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Size: ${pet.size}", fontSize = 20.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Characteristics: ${pet.characteristics}", fontSize = 18.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "About: ${pet.about}", fontSize = 18.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(16.dp))
    
                Button(onClick = { 
                if(isBookmarked){
                    sharedViewModel.removeBookmark(pet)
                }else{
                    sharedViewModel.bookmarkPet(pet)
                }
                }) {
                    Text(text = if(isBookmarked) "Remove Bookmark" else "Bookmark")
                }
                Spacer(modifier = Modifier.height(16.dp))
                
                Button(onClick = { /*TODO ADOPTION LOGIC*/ }) {
                    Text(text = "Adopt ${pet.name}")
                }
            }
        }
    }
}