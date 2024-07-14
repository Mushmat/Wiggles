package com.example.wigglesapp


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdoptionApplicationScreen(navController: NavController, petId: Int, sharedViewModel: SharedViewModel) {
    val pet = dummyPets.firstOrNull { it.id == petId } ?: return
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
    val answers = remember { mutableStateOf(List(questions.size) { "" }) }
    var currentQuestion by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Go for the PAW!!!") },
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
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = pet.imageUrl),
                    contentDescription = "Pet Chosen for Adoption",
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(16.dp)
                )
                Text(text = "Fur-ever Friendly", fontSize = 24.sp, color = Color(0xff1a1a73))
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Paw-sonal Name: ${pet.name}",
                    fontSize = 20.sp,
                    color = Color(0xFF800000)
                )
                Text(
                    text = "Paw-sonal Breed: ${pet.breed}",
                    fontSize = 20.sp,
                    color = Color(0xFF800000)
                )
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
                        Text(
                            text = questions[currentQuestion],
                            fontSize = 18.sp,
                            color = Color(0xFF1a1a73)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = answers.value[currentQuestion],
                            onValueChange = { newValue ->
                                answers.value = answers.value.toMutableList()
                                    .also { it[currentQuestion] = newValue }
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
    }
}