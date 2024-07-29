package com.example.wigglesapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wigglesapp.viewmodels.AuthViewModel
import com.example.wigglesapp.ui.components.DrawerButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun NavigationDrawer(navController: NavController, authViewModel: AuthViewModel, drawerState: DrawerState, scope:CoroutineScope) {
    // Column layout for the navigation drawer content
    Column(modifier = Modifier.padding(16.dp)) {
        // Home button
        DrawerButton(text = "Home") {
            scope.launch {

                navController.navigate("home")
                drawerState.close()
            }
        }
        // About Us button
        DrawerButton(text = "About Us") {
            scope.launch {
                navController.navigate("about_us_screen")
                drawerState.close()
            }
        }
        // User Profile button
        DrawerButton(text = "Purr-sonal Profile") {
            scope.launch {

                navController.navigate("user_profile_screen")
                drawerState.close()
            }
        }
        // Bookmarked Pets button
        DrawerButton(text = "Bookmarked Fur-iends") {
            scope.launch {

                navController.navigate("bookmarked_pets_screen")
                drawerState.close()}}
        // Adoption Tracker button
        DrawerButton(text = "Application Tracker") {
            scope.launch {

                navController.navigate("adoption_tracker")
                drawerState.close()}}
        // Testimonials button
        DrawerButton(text = "Tail Wagging Stories") {
            scope.launch {

                navController.navigate("testimonials_screen")
                drawerState.close()}}
        // FAQs button
        DrawerButton(text = "FAQs") {
            scope.launch {

                navController.navigate("faqs_screen")
                drawerState.close()}}

        // Contact Us button
        DrawerButton(text = "Get in Touch with Paws") {
            scope.launch {

                navController.navigate("contact_us_screen")
                drawerState.close()}}
        // Log Out button
        DrawerButton(text = "Paws Out") {
            scope.launch {

                authViewModel.logOut()
                drawerState.close()}
        }
    }
}
