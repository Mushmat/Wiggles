package com.example.wigglesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationDetailScreen(navController: NavController, petId: Int, sharedViewModel: SharedViewModel) {
    val application = sharedViewModel.adoptionApplications.collectAsState().value.firstOrNull { it.petId == petId } ?: return
    val pet = dummyPets.firstOrNull { it.id == petId } ?: return

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberImagePainter(data = pet.imageUrl),
            contentDescription = "Pet Image",
            modifier = Modifier
                .size(128.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Name: ${pet.name}", fontSize = 20.sp)
        Text(text = "Breed: ${pet.breed}", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))

        application.answers.forEachIndexed { index, answer ->
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
                    Text(text = "Q${index + 1}: ${getQuestionLabel(index)}", fontSize = 18.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = answer, fontSize = 18.sp)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "STATUS: IN PROGRESS", fontSize = 20.sp, color = Color.Green)
    }
}

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
