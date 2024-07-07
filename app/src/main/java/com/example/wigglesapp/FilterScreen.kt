package com.example.wigglesapp

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(navController: NavController, applyFilters: (String, String, String) -> Unit) {
    var selectedBreed by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("") }
    var selectedSize by remember { mutableStateOf("") }

    val breeds = listOf("Pit Bull Terrier", "Dachshund", "Plott Hound", "Hound", "Mixed Breed", "Labrador Retriever", "Jack Russell Terrier", "Australian Cattle Dog", "German Shepherd", "Domestic Short Hair", "American Short Hair", "Domestic Medium Hair", "Abyssinian", "Bengal", "British Short Hair", "Dilute Calico")
    val genders = listOf("Male", "Female")
    val sizes = listOf("Small", "Medium")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Filter Pets") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go Back")
                    }
                }
            )
        }
    ) {
        paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            item{
                Text(text = "Select Breed")
                breeds.forEach{
                        breed ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedBreed == breed,
                            onClick = { selectedBreed = breed }
                        )
                        Text(text = breed)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Select Gender")
                genders.forEach{
                        gender ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedBreed == gender,
                            onClick = { selectedBreed = gender }
                        )
                        Text(text = gender)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Select Size")
                sizes.forEach{
                        size ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedBreed == size,
                            onClick = { selectedBreed = size }
                        )
                        Text(text = size)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    applyFilters(selectedBreed, selectedGender, selectedSize)
                    navController.popBackStack()
                }) {
                    Text(text = "Apply Filters")
                }
            }
        }
    }
}