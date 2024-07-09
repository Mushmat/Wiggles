package com.example.wigglesapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetCareOptions(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Pet Care Options") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(R.drawable.baseline_arrow_back_24), contentDescription = null)
                    }
                }
            )
        }
    ) {
        paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ){
            Button(onClick = { navController.navigate("general_tips") }) {
                Text(text = "General Pet Care Tips")
            }
            Button(onClick = { navController.navigate("dog_tips") }) {
                Text(text = "Dog Care Tips")
            }
            Button(onClick = { navController.navigate("cat_tips") }) {
                Text(text = "Cat Care Tips")
            }
        }
    }
}
