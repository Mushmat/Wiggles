package com.example.wigglesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@Composable
fun PetDetailScreen(navController: NavController, petId: Int){
    val pet = dummyPets.firstOrNull{ it.id == petId} ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = pet.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .padding(16.dp)
        )

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

        Button(onClick = { /*TODO ADOPTION LOGIC*/ }) {
            Text(text = "Adopt ${pet.name}")
        }
    }
}