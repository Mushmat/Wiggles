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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, drawerState: DrawerState, scope: CoroutineScope, authViewModel: AuthViewModel) {

    val context = LocalContext.current
    val authState by authViewModel.authState.collectAsState()

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
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
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
    }
}

