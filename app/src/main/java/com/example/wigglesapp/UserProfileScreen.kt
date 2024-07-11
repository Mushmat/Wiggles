package com.example.wigglesapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController


@Composable
fun UserProfileScreen(navController: NavController, authViewModel: AuthViewModel){
    val authState by authViewModel.authState.collectAsState()

    var fullname by remember { mutableStateOf(authState.user?.fullname ?: "") }


}