package com.example.wigglesapp.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wigglesapp.R
import com.example.wigglesapp.ui.components.GradientButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetCareScreen(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Paw Care") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                GradientButton(
                    onClick = { navController.navigate("pet_care_tips_screen") },
                    text = "Pet Care Tips",
                    gradient = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFE91E63), Color(0xFFFFC107))
                    ),
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                GradientButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse("https://www.google.com/maps/search/vets+near+me")
                        }
                        context.startActivity(intent)
                    },
                    text = "Pet Docs nearby",
                    gradient = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF8E24AA), Color(0xFFEC407A))
                    ),
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                GradientButton(
                    onClick = { navController.navigate("parents_screen") },
                    text = "Paw-rents with my fur-iends",
                    gradient = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF3F51B5), Color(0xFF03A9F4))
                    ),
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


