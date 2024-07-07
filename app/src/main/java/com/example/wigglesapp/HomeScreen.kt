package com.example.wigglesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope

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


    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_image),
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
            Text(text = "Home", fontSize = 32.sp)
            Spacer(modifier = Modifier.height(16.dp))

            HomeButton(
                icon = R.drawable.baseline_pets_24,
                text = "Available Pets",
                onClick = { navController.navigate("available_pets") })
            HomeButton(
                icon = R.drawable.baseline_search_24,
                text = "Find Best Pet",
                onClick = { /* Navigate to Find Best Pet */ })
            HomeButton(
                icon = R.drawable.health,
                text = "Pet Care",
                onClick = { /* Navigate to Pet Care */ })
            HomeButton(
                icon = R.drawable.shelter,
                text = "Shelter Info",
                onClick = { /* Navigate to Shelter Info */ })
            HomeButton(
                icon = R.drawable.money,
                text = "Donate",
                onClick = { /* Navigate to Donate */ })
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start
        ) {
            Button(
                onClick = { authViewModel.logOut() },
            ) {
                Text(text = "Sign Out")
            }
        }
    }
}

@Composable
fun HomeButton(text: String, icon: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = icon), contentDescription = null, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, fontSize = 18.sp)
        }
    }
}
