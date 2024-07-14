package com.example.wigglesapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun NavigationDrawer(navController: NavController,authViewModel: AuthViewModel, drawerState: DrawerState, scope:CoroutineScope) {
    Column(modifier = Modifier.padding(16.dp)) {
        DrawerButton(text = "Paw-some Home") {
            scope.launch {
                drawerState.close()
                navController.navigate("home")
            }
        }
        DrawerButton(text = "Fur-tastic About Us") {
            scope.launch {
                drawerState.close()
                navController.navigate("about_us_screen")
            }
        }
        DrawerButton(text = "Purr-sonal Profile") {
            scope.launch {
                drawerState.close()
                navController.navigate("user_profile_screen")
            }
        }
        DrawerButton(text = "Favorite Fur-iends") {
            scope.launch {
                drawerState.close()
                navController.navigate("bookmarked_pets_screen") }}
        DrawerButton(text = "Pawgress Tracker") {
            scope.launch {
                drawerState.close()
                navController.navigate("adoption_tracker") }}
        DrawerButton(text = "Tail Wagging Stories") {
            scope.launch {
                drawerState.close()
                navController.navigate("testimonials_screen") }}
        DrawerButton(text = "Frequently Barked Questions") {
            scope.launch {
                drawerState.close()
                navController.navigate("faqs_screen") }}
        DrawerButton(text = "Get in Touch with Paws") {
            scope.launch {
                drawerState.close()
                navController.navigate("contact_us_screen") }}
        DrawerButton(text = "Paws Out") {
            scope.launch {
                drawerState.close()
                authViewModel.logOut()}
        }}
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