package com.example.wigglesapp


import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope

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



}