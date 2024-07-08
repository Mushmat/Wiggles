package com.example.wigglesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkedPetsScreen(navController: NavController, sharedViewModel: SharedViewModel){
    val bookmarkedPets by sharedViewModel.bookmarkedPets.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Bookmarked Pets") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)){
        if(bookmarkedPets.isEmpty()){
            Text(text = "No Bookmarked Pets Available", fontSize = 20.sp, modifier = Modifier.padding(16.dp))
        }else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                items(bookmarkedPets) { pet ->
                    BookmarkedPetCard(navController = navController, pet = pet)
                }
            }
        }
        }
    }
}

@Composable
fun BookmarkedPetCard(navController: NavController, pet: Pet){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate("pet_detail/${pet.id}") }
    ){
        Image(
            painter = rememberImagePainter(data = pet.imageUrl),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = pet.name, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
        Text(text = pet.breed, fontSize = 14.sp, color = Color.Gray)

    }
}