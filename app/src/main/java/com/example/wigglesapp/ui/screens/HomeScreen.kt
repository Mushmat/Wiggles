package com.example.wigglesapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wigglesapp.viewmodels.AuthViewModel
import com.example.wigglesapp.R
import com.example.wigglesapp.ui.components.HomeButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//Class for Tutorial

enum class TutorialStep{
    None, PetParade, PetMatcher, PetWellness, PawHouseInfo, Donate, Drawer
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, drawerState: DrawerState, scope: CoroutineScope, authViewModel: AuthViewModel) {
    // Get the authentication state from the view model
    val authState by authViewModel.authState.collectAsState()
    //State to remember the tutorial steps
    var tutorialStep by remember { mutableStateOf(TutorialStep.None) }

    // Check the authentication state and navigate to the auth screen if not authenticated
    LaunchedEffect(key1 = authState.isAuthenticated) {
        if (!authState.isAuthenticated) {
            navController.navigate("auth") {
                popUpTo(navController.graph.startDestinationId) { inclusive = true }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Home") },
                actions = {
                    IconButton(onClick = { scope.launch { drawerState.open() } }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_menu_24),
                            contentDescription = "Menu"
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.Transparent)
                    ) {
                        Text(
                            text = "Start Tutorial",
                            color = Color.Blue,
                            modifier = Modifier
                                .clickable { tutorialStep = TutorialStep.PetParade }
                                .padding(horizontal = 16.dp, vertical = 8.dp) // Added padding
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        // Main content container
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Background image
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                // Column layout to display the home screen content
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Display the title
                Text(text = "Paws Up!", fontSize = 32.sp)
                Spacer(modifier = Modifier.height(16.dp))

                // Home buttons for navigation
                HomeButton(
                    icon = R.drawable.baseline_pets_24,
                    text = "Pet Parade",
                    onClick = { navController.navigate("available_pets") },
                    highlight = tutorialStep == TutorialStep.PetParade
                )
                HomeButton(
                    icon = R.drawable.baseline_search_24,
                    text = "Pet Matcher",
                    onClick = { navController.navigate("pet_quiz") },
                    highlight = tutorialStep == TutorialStep.PetMatcher
                )
                HomeButton(
                    icon = R.drawable.health,
                    text = "Pet Wellness",
                    onClick = { navController.navigate("pet_care_screen") },
                    highlight = tutorialStep == TutorialStep.PetWellness
                    )
                HomeButton(
                    icon = R.drawable.shelter,
                    text = "Paw-house Info",
                    onClick = { navController.navigate("shelter_info") },
                    highlight = tutorialStep == TutorialStep.PawHouseInfo
                )
                HomeButton(
                    icon = R.drawable.money,
                    text = "Donate (Coming Soon)",
                    onClick = { /* Navigate to Donate */ },
                    highlight = tutorialStep == TutorialStep.Donate
                )
            }

            when (tutorialStep) {
                TutorialStep.PetParade -> TutorialOverlay("This is Pet Parade. Click here to see available pets.") {
                    tutorialStep = TutorialStep.PetMatcher
                }
                TutorialStep.PetMatcher -> TutorialOverlay("This is Pet Matcher. Click here to take a quiz and find your perfect pet.") {
                    tutorialStep = TutorialStep.PetWellness
                }
                TutorialStep.PetWellness -> TutorialOverlay("This is Pet Wellness. Click here for tips and services on pet care.") {
                    tutorialStep = TutorialStep.PawHouseInfo
                }
                TutorialStep.PawHouseInfo -> TutorialOverlay("This is Paw-house Info. Click here for information on shelters.") {
                    tutorialStep = TutorialStep.Donate
                }
                TutorialStep.Donate -> TutorialOverlay("This is Donate. You can donate here soon.") {
                    tutorialStep = TutorialStep.Drawer
                }
                TutorialStep.Drawer -> TutorialOverlay("This is the menu. Swipe from left to right to open the drawer or click the icon above.") {
                    tutorialStep = TutorialStep.None
                }
                else -> {}
            }
        }
    }
}

@Composable
fun TutorialOverlay(message: String, onDismiss: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x80000000))
            .clickable { onDismiss() }
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .padding(24.dp)
        ) {
            Text(
                text = message,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}