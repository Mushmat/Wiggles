package com.example.wigglesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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

@Composable
fun HomeButton(text: String, icon: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.horizontalGradient(
                    listOf(Color(0xFF42A5F5), Color(0xFF1E88E5))
                )
            )
            .shadow(8.dp, RoundedCornerShape(12.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White, shape = CircleShape)
                    .padding(8.dp)
            ) {
                Image(painter = painterResource(id = icon), contentDescription = null, modifier = Modifier.fillMaxSize())
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, fontSize = 18.sp, color = Color.White)
        }
    }
}
