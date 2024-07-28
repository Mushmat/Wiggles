package com.example.wigglesapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.wigglesapp.R
import com.example.wigglesapp.ui.components.CardSection
import com.example.wigglesapp.ui.components.SectionContent

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
                    .verticalScroll(rememberScrollState())
            ) {
                val imageUrl = "https://i.insider.com/6589dc041c5c7b8c9a0beb71?width=700"
                Image(
                    painter = rememberAsyncImagePainter(model = imageUrl),
                    contentDescription = "Shelter Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .padding(bottom = 16.dp)
                )

                CardSection(title = "Shelter Overview") {
                    SectionContent(text = "Our shelter is dedicated to rescuing and rehoming pets. We strive to provide a safe and loving environment for all animals in our care until they find their forever homes.")
                }

                CardSection(title = "Contact Information") {
                    SectionContent(text = "Address: 123 Pet Street, Animal City, AC 12345")
                    SectionContent(text = "Phone: (123) 456-7890")
                    SectionContent(text = "Email: contact@shelter.org")
                    SectionContent(text = "Operating Hours: Mon-Sat, 9 AM - 6 PM")
                }

                CardSection(title = "Adoption Process") {
                    SectionContent(text = "1. Go to the Pet Parade or find a pet through Pet Matcher.\n2. Click on the Adopt Button and fill out the form.\n3. Track the status of the request from the drawer.\n4. You will be informed when your request is accepted.\n5. Take your new pet home!")
                }

                CardSection(title = "Volunteer Opportunities") {
                    SectionContent(text = "Join our team of dedicated volunteers! We have various roles available, including animal care, event support, and administrative tasks. Contact us to learn more and get involved.")
                }

                CardSection(title = "Donation Information") {
                    SectionContent(text = "Support our shelter by donating! We accept monetary donations, as well as supplies such as food, toys, and bedding. Visit our website for more details on how to donate.")
                }

                CardSection(title = "Events and Programs") {
                    SectionContent(text = "Stay updated on our upcoming events and programs. We host adoption events, workshops, and community outreach programs. Check our website or contact us for more information.")
                }

                CardSection(title = "Success Stories") {
                    SectionContent(text = "Read heartwarming stories from individuals and families who have adopted pets from our shelter. Their stories inspire and motivate us to continue our mission.")
                }
            }
        }
    }
}
