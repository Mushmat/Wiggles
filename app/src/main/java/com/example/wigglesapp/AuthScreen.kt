package com.example.wigglesapp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun AuthScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {
    var isSignUp by remember { mutableStateOf(false) }
    val authState by authViewModel.authState.collectAsState()

    if (authState.isAuthenticated) {
        navController.navigate("home") {
            popUpTo("auth") { inclusive = true }
        }
    } else {
        if (isSignUp) {
            SignUpScreen(authViewModel) { isSignUp = false }
        } else {
            LoginScreen(authViewModel) { isSignUp = true }
        }
    }
}
