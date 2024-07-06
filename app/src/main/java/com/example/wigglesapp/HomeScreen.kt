package com.example.wigglesapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun HomeScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Wiggles") },
                navigationIcon = {
                    IconButton(onClick = { /*TODO: OPEN NAVIGATION DRAWER HERE*/ }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_menu_24),
                            contentDescription = "Menu")
                    }
                }
            )
        },
        content = {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                HomeOptionItem("Available Pets", R.drawable.baseline_pets_24){
                    navController.navigate("availablePets")
                }
                Spacer(modifier = Modifier.height(16.dp))

                HomeOptionItem("Don't know which pet to go with?", R.drawable.baseline_pets_24){
                    navController.navigate("findBestPet")
                }
                Spacer(modifier = Modifier.height(16.dp))

                HomeOptionItem("Pet Care", R.drawable.baseline_pets_24){
                    navController.navigate("petCare")
                }
                Spacer(modifier = Modifier.height(16.dp))

                HomeOptionItem("Shelter Info", R.drawable.baseline_pets_24){
                    navController.navigate("shelterInfo")
                }
                Spacer(modifier = Modifier.height(16.dp))

                HomeOptionItem("Donate", R.drawable.baseline_pets_24){
                    navController.navigate("donate")
                }
            }
        }
    )
}