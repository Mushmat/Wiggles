package com.example.wigglesapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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

                navController.navigate("home")
                drawerState.close()
            }
        }
        DrawerButton(text = "Fur-tastic About Us") {
            scope.launch {
                navController.navigate("about_us_screen")
                drawerState.close()
            }
        }
        DrawerButton(text = "Purr-sonal Profile") {
            scope.launch {

                navController.navigate("user_profile_screen")
                drawerState.close()
            }
        }
        DrawerButton(text = "Favorite Fur-iends") {
            scope.launch {

                navController.navigate("bookmarked_pets_screen")
                drawerState.close()}}
        DrawerButton(text = "Pawgress Tracker") {
            scope.launch {

                navController.navigate("adoption_tracker")
                drawerState.close()}}
        DrawerButton(text = "Tail Wagging Stories") {
            scope.launch {

                navController.navigate("testimonials_screen")
                drawerState.close()}}
        DrawerButton(text = "Frequently Barked Questions") {
            scope.launch {

                navController.navigate("faqs_screen")
                drawerState.close()}}
        DrawerButton(text = "Get in Touch with Paws") {
            scope.launch {

                navController.navigate("contact_us_screen")
                drawerState.close()}}
        DrawerButton(text = "Paws Out") {
            scope.launch {

                authViewModel.logOut()
                drawerState.close()}
        }}
}

@Composable
fun DrawerButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.horizontalGradient(
                    listOf(Color(0xFFFFC1E3), Color(0xFFFF8A80))
                )
            )
            .shadow(8.dp, RoundedCornerShape(12.dp)),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Text(text = text, fontSize = 18.sp, color = Color.White)
    }
}