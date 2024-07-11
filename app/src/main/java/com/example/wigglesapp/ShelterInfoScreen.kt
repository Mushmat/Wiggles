package com.example.wigglesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShelterInfoScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Shelter Information") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            val imageUrl = "https://i.insider.com/6589dc041c5c7b8c9a0beb71?width=700"
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "Shelter Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 16.dp)
            )
            
            Text(text = "Shelter Overview (Dummy Data)", fontSize = 24.sp, modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = "Our shelter is dedicated to rescuing and rehoming pets. We strive to provide a safe and loving environment for all animals in our care until they find their forever homes.",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(text = "Contact Information", fontSize = 24.sp, modifier = Modifier.padding(vertical = 8.dp))
            Text(text = "Address: 123 Pet Street, Animal City, AC 12345", fontSize = 16.sp)
            Text(text = "Phone: (123) 456-7890", fontSize = 16.sp)
            Text(text = "Email: contact@shelter.org", fontSize = 16.sp)
            Text(text = "Operating Hours: Mon-Sat, 9 AM - 6 PM", fontSize = 16.sp, modifier = Modifier.padding(bottom = 16.dp))

            Text(text = "Adoption Process", fontSize = 24.sp, modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = "1. Visit the shelter to meet the pets.\n2. Fill out an adoption application.\n3. Meet with an adoption counselor.\n4. Complete the adoption paperwork and pay the adoption fee.\n5. Take your new pet home!",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(text = "Volunteer Opportunities", fontSize = 24.sp, modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = "Join our team of dedicated volunteers! We have various roles available, including animal care, event support, and administrative tasks. Contact us to learn more and get involved.",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(text = "Donation Information", fontSize = 24.sp, modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = "Support our shelter by donating! We accept monetary donations, as well as supplies such as food, toys, and bedding. Visit our website for more details on how to donate.",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(text = "Events and Programs", fontSize = 24.sp, modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = "Stay updated on our upcoming events and programs. We host adoption events, workshops, and community outreach programs. Check our website or contact us for more information.",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(text = "Success Stories", fontSize = 24.sp, modifier = Modifier.padding(vertical = 8.dp))
            Text(
                text = "Read heartwarming stories from individuals and families who have adopted pets from our shelter. Their stories inspire and motivate us to continue our mission.",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
        }
    }
}