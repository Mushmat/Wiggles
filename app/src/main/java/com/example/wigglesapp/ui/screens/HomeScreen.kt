package com.example.wigglesapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.wigglesapp.viewmodels.AuthViewModel
import com.example.wigglesapp.R
import com.example.wigglesapp.ui.components.HomeButton
import com.example.wigglesapp.ui.components.TutorialButton
import com.example.wigglesapp.ui.components.TutorialOverlay
import com.example.wigglesapp.utils.NotificationUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class TutorialStep {
    None, PetParade, PetMatcher, PetWellness, PawHouseInfo, Donate, Drawer
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, drawerState: DrawerState, scope: CoroutineScope, authViewModel: AuthViewModel) {
    val authState by authViewModel.authState.collectAsState()
    var tutorialStep by remember { mutableStateOf(TutorialStep.None) }
    var highlightPosition by remember { mutableStateOf(Offset.Zero) }
    var highlightSize by remember { mutableStateOf(Size.Zero) }

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
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Paws Up!", fontSize = 32.sp)
                Spacer(modifier = Modifier.height(16.dp))

               TutorialButton(
                icon = R.drawable.baseline_tour_24,
                text = "Start Tutorial",
                onClick = { tutorialStep = TutorialStep.PetParade },
                highlight = false,
                onGloballyPositioned = {}
            )

                Spacer(modifier = Modifier.height(8.dp))

                HomeButton(
                    icon = R.drawable.baseline_pets_24,
                    text = "Pet Parade",
                    onClick = { navController.navigate("available_pets") },
                    highlight = tutorialStep == TutorialStep.PetParade,
                    onGloballyPositioned = { coordinates ->
                        if (tutorialStep == TutorialStep.PetParade) {
                            highlightPosition = coordinates.positionInRoot()
                            highlightSize = coordinates.size.toSize()
                        }
                    }
                )

                HomeButton(
                    icon = R.drawable.baseline_search_24,
                    text = "Pet Matcher",
                    onClick = {
                        navController.navigate("pet_quiz")
                    },
                    highlight = tutorialStep == TutorialStep.PetMatcher,
                    onGloballyPositioned = { coordinates ->
                        if (tutorialStep == TutorialStep.PetMatcher) {
                            highlightPosition = coordinates.positionInRoot()
                            highlightSize = coordinates.size.toSize()
                        }
                    }
                )

                HomeButton(
                    icon = R.drawable.health,
                    text = "Pet Wellness",
                    onClick = {
                        navController.navigate("pet_care_screen")
                              },
                    highlight = tutorialStep == TutorialStep.PetWellness,
                    onGloballyPositioned = { coordinates ->
                        if (tutorialStep == TutorialStep.PetWellness) {
                            highlightPosition = coordinates.positionInRoot()
                            highlightSize = coordinates.size.toSize()
                        }
                    }
                )
                HomeButton(
                    icon = R.drawable.shelter,
                    text = "Paw-house Info",
                    onClick = { navController.navigate("shelter_info") },
                    highlight = tutorialStep == TutorialStep.PawHouseInfo,
                    onGloballyPositioned = { coordinates ->
                        if (tutorialStep == TutorialStep.PawHouseInfo) {
                            highlightPosition = coordinates.positionInRoot()
                            highlightSize = coordinates.size.toSize()
                        }
                    }
                )
                HomeButton(
                    icon = R.drawable.money,
                    text = "Donate (Coming Soon)",
                    onClick = { /* Navigate to Donate */ },
                    highlight = tutorialStep == TutorialStep.Donate,
                    onGloballyPositioned = { coordinates ->
                        if (tutorialStep == TutorialStep.Donate) {
                            highlightPosition = coordinates.positionInRoot()
                            highlightSize = coordinates.size.toSize()
                        }
                    }
                )
            }

            if (tutorialStep != TutorialStep.None) {
                TutorialOverlay(
                    message = when (tutorialStep) {
                        TutorialStep.PetParade -> "This is Pet Parade. Click here to see available pets."
                        TutorialStep.PetMatcher -> "This is Pet Matcher. Click here to take a quiz and find your perfect pet."
                        TutorialStep.PetWellness -> "This is Pet Wellness. Click here for tips and services on pet care."
                        TutorialStep.PawHouseInfo -> "This is Paw-house Info. Click here for information on shelters."
                        TutorialStep.Donate -> "This is Donate. You can donate here soon."
                        TutorialStep.Drawer -> "This is the menu. Swipe from left to right to open the drawer or click the icon above."
                        else -> ""
                    },
                    highlightPosition = highlightPosition,
                    highlightSize = highlightSize,
                    onDismiss = {
                        tutorialStep = when (tutorialStep) {
                            TutorialStep.PetParade -> TutorialStep.PetMatcher
                            TutorialStep.PetMatcher -> TutorialStep.PetWellness
                            TutorialStep.PetWellness -> TutorialStep.PawHouseInfo
                            TutorialStep.PawHouseInfo -> TutorialStep.Donate
                            TutorialStep.Donate -> TutorialStep.Drawer
                            TutorialStep.Drawer -> TutorialStep.None
                            else -> TutorialStep.None
                        }
                    }
                )
            }
        }
    }
}
