package com.example.wigglesapp.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wigglesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactUsScreen(navController: NavController) {
    // Get the current context
    val context = LocalContext.current
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Get in Touch with Paws") },
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
            Box(modifier = Modifier.fillMaxSize()) {
                // Background image
                Image(
                    painter = painterResource(id = R.drawable.bg),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // Column layout to display contact information and buttons
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Contact Us image
                Image(
                    painter = painterResource(id = R.drawable.contact_us_image),
                    contentDescription = "Contact Us Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
                // Title text
                Text(
                    text = "Get in Touch with Paws",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black,
                    fontSize = 24.sp
                )

                Spacer(modifier = Modifier.height(8.dp))
                // Contact email
                Text(
                    text = "Email us at: contact@wigglesapp.com",
                    fontSize = 18.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Contact phone number
                Text(
                    text = "Call us at: 8114489928",
                    fontSize = 18.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(4.dp))

                // General queries text
                Text(
                    text = "For general queries,",
                    fontSize = 18.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Button to open Google Form
                Button(
                    onClick = {
                        // Intent to open the Google Form link
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse("https://forms.gle/fjPHCgGzySoHpq3x7")
                        }
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_format_list_bulleted_24),
                        contentDescription = "Google Form Icon",
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = "Google Form")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Developer:",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black,
                    fontSize = 24.sp
                )
                Text(
                    text = "Chirayu Choudhary",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.ExtraBold),
                    color = Color.Black,
                    fontSize = 24.sp
                )
            }
        }
    }
}