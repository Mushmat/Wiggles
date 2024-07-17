package com.example.wigglesapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wigglesapp.R
import com.example.wigglesapp.ui.components.GradientButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetCareOptions(navController: NavController){

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Paw Care Options") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_arrow_back_24),
                            contentDescription = null
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                GradientButton(
                    onClick = { navController.navigate("general_tips") },
                    text = "General Pet Care Tips",
                    gradient = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFFFA726), Color(0xFFFF7043))
                    ),
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                GradientButton(
                    onClick = { navController.navigate("dog_tips") },
                    text = "Dog Care Tips",
                    gradient = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF42A5F5), Color(0xFF1976D2))
                    ),
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                GradientButton(
                    onClick = { navController.navigate("cat_tips") },
                    text = "Cat Care Tips",
                    gradient = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF66BB6A), Color(0xFF388E3C))
                    ),
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                )
            }
        }
    }
}

