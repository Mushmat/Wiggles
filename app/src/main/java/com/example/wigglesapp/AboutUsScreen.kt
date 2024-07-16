package com.example.wigglesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Fur-tastic About Us", color = Color(0xFF000000)) },
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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    painter = painterResource(id = R.drawable._024), // Add your shelter image here
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .padding(16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Welcome to Wiggles",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6200EE)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Your one-stop solution for finding and adopting the perfect pet! Our mission is to connect loving homes with pets in need and ensure that every pet finds a forever home. At Wiggles, we believe in the transformative power of pet adoption and strive to make the process as seamless and joyful as possible.",
                    fontSize = 16.sp,
                    color = Color(0xff1a1a73),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader(title = "Paws for a Cause")
                Text(
                    text = "Our mission is to provide a platform that facilitates the adoption process, making it easier for you to find a pet that matches your lifestyle and preferences. We aim to:\n\n- Promote Pet Adoption\n- Provide Comprehensive Information\n- Support Pet Owners",
                    fontSize = 16.sp,
                    color = Color(0xff1a1a73),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader(title = "Tail Tales")
                Text(
                    text = "At Wiggles, we offer a range of features designed to help you find and adopt the perfect pet:\n\n- Detailed Pet Profiles\n- Personalized Recommendations\n- Pet Care Tips\n- Nearby Vets\n- Community Support",
                    fontSize = 16.sp,
                    color = Color(0xff1a1a73),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader(title = "Paws & Perks")
                Text(
                    text = "User-Friendly Interface\nComprehensive Resources\nCommitment to Pet Welfare",
                    fontSize = 16.sp,
                    color = Color(0xff1a1a73),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader(title = "Get in Touch with Paws")
                Text(
                    text = "If you have any questions or need assistance, feel free to reach out to us at contact@wigglesapp.com. We're here to help!",
                    fontSize = 16.sp,
                    color = Color(0xff1a1a73),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF6200EE),
        modifier = Modifier
            .padding(vertical = 8.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFFFFA726), Color(0xFFFF7043))
                )
            )
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    )
}
