package com.example.wigglesapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.wigglesapp.R
import com.example.wigglesapp.utils.NotificationUtils
import com.example.wigglesapp.viewmodels.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdoptionTrackerScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    // Collect the list of adoption applications from the shared ViewModel
    val adoptionApplications by sharedViewModel.adoptionApplications.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Paw-plications") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        // Main content container
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            // Background image
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            // Column layout to display the list of applications
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Title text for the screen
                Text(
                    text = "Paw-plications:",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(16.dp), color = Color(0xFF1a1a73)
                )

                // Check if there are any applications
                if (adoptionApplications.isEmpty()) {
                    // Display a message if no applications are found
                    Text(
                        text = "No Paw-plications Found!",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(16.dp), color = Color(0xFF1a1a73)
                    )
                } else {
                    adoptionApplications.forEach { application ->
                        // Loop through each application and display its details
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clickable { navController.navigate("application_detail/${application.petId}") },
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            // Row layout to display the pet image and details
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Display the pet image
                                Image(
                                    painter = rememberAsyncImagePainter(model = dummyPets.first { it.id == application.petId }.imageUrl),
                                    contentDescription = "Pet Image",
                                    modifier = Modifier
                                        .size(64.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                // Display the pet name and application status
                                Column {
                                    Text(
                                        text = dummyPets.first { it.id == application.petId }.name,
                                        fontSize = 20.sp
                                    )
                                    Text(
                                        text = "Status: ${application.status}",
                                        fontSize = 16.sp, color = when(application.status) {
                                            "ACCEPTED" -> Color(0xFF2e7d32)
                                            "DENIED" -> Color(0xFFD32F2F)
                                            else -> Color(0xFFFFA000)
                                        }
                                    )
                                    Text(text = "Remarks: ${application.remarks}", fontSize = 14.sp, color = Color(0xFF2e7d32))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
