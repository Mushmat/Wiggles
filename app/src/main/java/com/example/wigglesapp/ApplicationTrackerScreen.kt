package com.example.wigglesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import kotlinx.coroutines.CoroutineScope

@Composable
fun AdoptionTrackerScreen(navController: NavController, drawerState: DrawerState, scope: CoroutineScope, sharedViewModel: SharedViewModel){
    val adoptionApplications by sharedViewModel.adoptionApplications.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Adoption Applications:", fontSize = 24.sp, modifier = Modifier.padding(16.dp))

        if(adoptionApplications.isEmpty()) {
            Text(text = "No applications found!", fontSize = 18.sp, modifier = Modifier.padding(16.dp))
        } else {
            adoptionApplications.forEach { application ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable { navController.navigate("application_detail/${application.petId}") },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = rememberImagePainter(data = dummyPets.first { it.id == application.petId }.imageUrl),
                            contentDescription = "Pet Image",
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(text = dummyPets.first { it.id == application.petId }.name, fontSize = 20.sp)
                            Text(text = "Status: IN PROGRESS", fontSize = 16.sp, color = Color.Green)
                        }
                    }
                }
            }
        }
    }
}