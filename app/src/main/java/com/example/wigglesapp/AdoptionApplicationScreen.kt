package com.example.wigglesapp


import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@Composable
fun AdoptionApplicationScreen(navController: NavController, petId: Int, sharedViewModel: SharedViewModel){
    val pet = dummyPets.firstOrNull {it.id == petId} ?: return
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
    val answers = remember { mutableStateOf(List(questions.size) {""}) }
    var currentQuestion by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = rememberImagePainter(data = pet.imageUrl),
            contentDescription = "Pet Chosen for Adoption",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Text(text = "Pet Chosen for Adoption", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Name: ${pet.name}", fontSize = 20.sp)
        Text(text = "Breed: ${pet.breed}", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))

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
                Text(text = questions[currentQuestion], fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = answers.value[currentQuestion],
                    onValueChange = { newValue ->
                        answers.value = answers.value.toMutableList().also {it[currentQuestion] = newValue}
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (currentQuestion > 0) {
                        Button(onClick = { currentQuestion-- }) {
                            Text(text = "Back")
                        }
                    }
                    if (currentQuestion < questions.size - 1) {
                        Button(onClick = { currentQuestion++ }) {
                            Text(text = "Next")
                        }
                    } else {
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