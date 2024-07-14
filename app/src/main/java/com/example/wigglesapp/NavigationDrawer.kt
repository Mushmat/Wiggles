package com.example.wigglesapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun NavigationDrawer(navController: NavController,authViewModel: AuthViewModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        DrawerButton(text = "Paw-some Home") { navController.navigate("home") }
        DrawerButton(text = "Fur-tastic About Us") { navController.navigate("about_us_screen") }
        DrawerButton(text = "Purr-sonal Profile") { navController.navigate("user_profile_screen") }
        DrawerButton(text = "Favorite Fur-iends") { navController.navigate("bookmarked_pets_screen") }
        DrawerButton(text = "Pawgress Tracker") { navController.navigate("adoption_tracker") }
        DrawerButton(text = "Tail Wagging Stories") { navController.navigate("testimonials_screen") }
        DrawerButton(text = "Frequently Barked Questions") { navController.navigate("faqs_screen") }
        DrawerButton(text = "Get in Touch with Paws") { navController.navigate("contact_us_screen") }
        DrawerButton(text = "Paws Out") {authViewModel.logOut()}
        }
}

@Composable
fun DrawerButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = text, fontSize = 18.sp)
    }
}