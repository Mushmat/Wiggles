package com.example.wigglesapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wigglesapp.viewmodels.AuthViewModel
import com.example.wigglesapp.R
import com.example.wigglesapp.ui.components.HomeButton
import com.example.wigglesapp.utils.PreferencesHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, drawerState: DrawerState, scope: CoroutineScope, authViewModel: AuthViewModel) {
    //CONTEXT
    val context = LocalContext.current
    // Get the authentication state from the view model
    val authState by authViewModel.authState.collectAsState()

    //Variable to store the state of greeting animation display
    var showGreeting by remember { mutableStateOf(!PreferencesHelper.isGreetingShown(context)) }

    // Check the authentication state and navigate to the auth screen if not authenticated
    LaunchedEffect(key1 = authState.isAuthenticated) {
        if(!authState.isAuthenticated){
            navController.navigate("auth"){
                popUpTo(navController.graph.startDestinationId) {inclusive = true}
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
        // Main content container
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
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
                    onClick = { navController.navigate("available_pets") })
                HomeButton(
                    icon = R.drawable.baseline_search_24,
                    text = "Pet Matcher",
                    onClick = { navController.navigate("pet_quiz") })
                HomeButton(
                    icon = R.drawable.health,
                    text = "Pet Wellness",
                    onClick = { navController.navigate("pet_care_screen") })
                HomeButton(
                    icon = R.drawable.shelter,
                    text = "Paw-house Info",
                    onClick = { navController.navigate("shelter_info") })
                HomeButton(
                    icon = R.drawable.money,
                    text = "Donate (Coming Soon)",
                    onClick = { /* Navigate to Donate */ })
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        AnimatedVisibility(
            visible = showGreeting,
            enter = fadeIn(animationSpec = tween(1000)) + slideInVertically(initialOffsetY = { it / 2}, animationSpec = tween(1000)),
            exit = fadeOut(animationSpec = tween(1000)) + slideOutVertically(targetOffsetY = { it / 2 }, animationSpec = tween(1000))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable {
                        showGreeting = false
                               PreferencesHelper.setGreetingShown(context, true)
                               },
                contentAlignment = Alignment.BottomStart
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Image(
                        painter = painterResource(id = R.drawable.animated_dog_image),
                        contentDescription = "Dog Icon",
                        modifier = Modifier.size(64.dp)
                    )
                    Text(text = "Hey, it's good to see you!", fontSize = 20.sp, color = Color.Black)
                }
            }
        }
    }
}

