package com.example.wigglesapp


import androidx.compose.foundation.layout.*
import androidx.compose.material3.DrawerState


import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.platform.LocalContext

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope


@Composable
fun BookmarkedPetsScreen(navController: NavController, drawerState: DrawerState, scope: CoroutineScope) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Bookmark")

    }
}
