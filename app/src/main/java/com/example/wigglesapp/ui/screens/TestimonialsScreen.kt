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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wigglesapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestimonialsScreen(navController: NavController){
    // List of testimonials
    val testimonials = listOf(
        "John Doe - Adopted Luna, the Golden Retriever: \"Adopting Luna through Wiggles was a seamless experience. She has brought so much joy to our family. The app made it easy to find the perfect pet for us. Highly recommend Wiggles to anyone looking to adopt a pet!\"",
        "Jane Smith - Adopted Max, the Beagle: \"Wiggles made the adoption process so easy and stress-free. Max is the perfect addition to our home. The app's interface is user-friendly, and the support team was incredibly helpful.\"",
        "Michael Johnson - Adopted Bella, the Boxer: \"Bella has been a wonderful companion, and we owe it all to Wiggles. The app helped us find a pet that fits perfectly with our lifestyle. The detailed profiles and photos were extremely helpful in making our decision.\"",
        "Emily Davis - Adopted Charlie, the Shih Tzu: \"We couldn't be happier with our experience using Wiggles to adopt Charlie. The app provided all the information we needed, and the adoption process was smooth and efficient. Charlie has become an inseparable part of our family.\"",
        "David Wilson - Adopted Daisy, the Cocker Spaniel: \"Wiggles is a fantastic platform for pet adoption. The detailed profiles, easy navigation, and supportive community made our adoption journey a pleasant one. Daisy has settled in perfectly, and we are grateful to Wiggles for bringing her into our lives.\"",
        "Sarah Brown - Adopted Cooper, the Poodle: \"Adopting Cooper was the best decision we ever made, and Wiggles made it possible. The app's features and support team were invaluable throughout the process. Cooper has brought so much happiness to our home.\"",
        "Jessica Taylor - Adopted Molly, the Dachshund: \"Wiggles made finding and adopting Molly a breeze. The app's comprehensive profiles and photos helped us make an informed decision. Molly has been a wonderful addition to our family, and we couldn't be happier.\"",
        "Christopher Martinez - Adopted Buddy, the Chihuahua: \"Buddy is an amazing pet, and we have Wiggles to thank for that. The app's user-friendly interface and detailed information made the adoption process straightforward. Buddy has brought so much love into our home.\"",
        "Ashley Anderson - Adopted Lucy, the Border Collie: \"Wiggles is a fantastic resource for anyone looking to adopt a pet. The app made it easy to find the perfect match for our family. Lucy has settled in wonderfully, and we are so grateful to Wiggles for connecting us.\"",
        "Daniel Thomas - Adopted Rocky, the Pomeranian: \"Rocky is a sweetheart, and we found him through Wiggles. The app provided all the necessary information and made the adoption process smooth and efficient. We are thrilled to have Rocky as part of our family.\""
    )

    Scaffold(
        topBar = {
            TopAppBar(
                // Top AppBar with title and back navigation
                title = { Text(text = "Tail Wagging Stories") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                // Background image
                painter = painterResource(id = R.drawable.bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        LazyColumn(
            // LazyColumn to display testimonials
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Iterate over testimonials and display each one in a Card
            items(testimonials) { testimonial ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        // Split the testimonial into name and message
                        val parts = testimonial.split(":")
                        Text(text = parts[0], fontSize = 18.sp, color = Color(0xFFff1493))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = parts[1], fontSize = 16.sp, color = Color(0xFF1a1a73))
                    }
                }
            }
        }}
    }
}