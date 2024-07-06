package com.example.wigglesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

                HomeOptionItem("Don't know which pet to go with?", R.drawable.baseline_search_24){
                    navController.navigate("findBestPet")
                }
                Spacer(modifier = Modifier.height(16.dp))

                HomeOptionItem("Pet Care", R.drawable.health){
                    navController.navigate("petCare")
                }
                Spacer(modifier = Modifier.height(16.dp))

                HomeOptionItem("Shelter Info", R.drawable.shelter){
                    navController.navigate("shelterInfo")
                }
                Spacer(modifier = Modifier.height(16.dp))

                HomeOptionItem("Donate", R.drawable.money){
                    navController.navigate("donate")
                }
            }
        }
    )
}

@Composable
fun HomeOptionItem(text: String, icon: Int, onClick: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = icon), contentDescription = text, modifier = Modifier.size(48.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp))
    }
}

@