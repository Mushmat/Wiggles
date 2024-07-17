package com.example.wigglesapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.wigglesapp.viewmodels.AuthViewModel

@Composable
fun AuthScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {
    // Track whether the user is on the sign-up screen or the login screen

    var isSignUp by remember { mutableStateOf(false) }

    // Collect the authentication state from the view model

    val authState by authViewModel.authState.collectAsState()
    // Check if the user is authenticated
    if (authState.isAuthenticated) {
        // Navigate to the home screen and clear the back stack
        navController.navigate("home") {
            popUpTo("auth") { inclusive = true }
        }
    } else {
        // Show the sign-up screen if the user is in sign-up mode, otherwise show the login screen
        if (isSignUp) {
            SignUpScreen(authViewModel) { isSignUp = false }
        } else {
            LoginScreen(authViewModel) { isSignUp = true }
        }
    }
}
