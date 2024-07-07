package com.example.wigglesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailScreen(navController: NavController, petId: Int, viewModel: PetViewModel = hiltViewModel()) {
    val pet = remember { dummyPets.find { it.id == petId } }
    val bookmarkedPets by viewModel.bookmarkedPets.collectAsState(initial = emptyList())
    val isBookmarked = bookmarkedPets.any { it.id == petId }

    pet?.let {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = pet.name) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "Back")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Image(
                    painter = rememberImagePainter(data = pet.imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(250.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Breed: ${pet.breed}", fontSize = 20.sp)
                Text(text = "Gender: ${pet.gender}", fontSize = 20.sp)
                Text(text = "Size: ${pet.size}", fontSize = 20.sp)
                Text(text = "Characteristics: ${pet.characteristics}", fontSize = 20.sp)
                Text(text = "About: ${pet.about}", fontSize = 20.sp)

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    if (isBookmarked) {
                        viewModel.removeBookmark(pet.id)
                    } else {
                        viewModel.bookmarkPet(pet)
                    }
                }) {
                    Text(text = if (isBookmarked) "Remove Bookmark" else "Bookmark")
                }
            }
        }
    }
}
