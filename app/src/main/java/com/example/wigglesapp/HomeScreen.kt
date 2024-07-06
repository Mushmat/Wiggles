package com.example.wigglesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(navController: NavController, scaffoldState: ScaffoldState, scope: CoroutineScope){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Home", fontSize = 32.sp)
        Spacer(modifier = Modifier.height(16.dp))

        HomeButton(icon = R.drawable.baseline_pets_24, text = "Available Pets", onClick = { /* Navigate to Available Pets */ })
        HomeButton(icon = R.drawable.baseline_search_24, text = "Find Best Pet", onClick = { /* Navigate to Find Best Pet */ })
        HomeButton(icon = R.drawable.health, text = "Pet Care", onClick = { /* Navigate to Pet Care */ })
        HomeButton(icon = R.drawable.shelter, text = "Shelter Info", onClick = { /* Navigate to Shelter Info */ })
        HomeButton(icon = R.drawable.money, text = "Donate", onClick = { /* Navigate to Donate */ })
    }
}

@Composable
fun HomeButton(text: String, icon: Int, onClick: () -> Unit){
   Button(
       onClick = onClick,
       modifier = Modifier
           .fillMaxWidth()
           .padding(vertical = 8.dp)
   ) {
       Row(
           verticalAlignment = Alignment.CenterVertically
       ) {
           Image(painter = painterResource(id = icon), contentDescription = null, modifier = Modifier.size(24.dp))
           Spacer(modifier = Modifier.width(8.dp))
           Text(text = text, fontSize = 18.sp)
       }
   }
}

