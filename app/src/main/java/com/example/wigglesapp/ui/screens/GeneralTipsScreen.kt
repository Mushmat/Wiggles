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
import androidx.compose.material3.Card
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wigglesapp.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun GeneralTipsScreen(navController: NavController) {
    val tips = listOf(
        "1. REGULAR VETERINARY CHECKUPS: Schedule regular visits to the vet for vaccinations and health checkups.",
        "2. BALANCED DIET: Provide a well-balanced diet suitable for your pet's age, size, and breed.",
        "3. FRESH WATER: Ensure your pet always has access to fresh, clean water.",
        "4. EXERCISE: Give your pet regular exercise to keep them healthy and prevent obesity.",
        "5. GROOMING: Regularly groom your pet to maintain their coat and check for any abnormalities.",
        "6. TRAINING: Invest time in training your pet to follow basic commands and good behavior.",
        "7. SAFE ENVIRONMENT: Create a safe and comfortable living environment for your pet.",
        "8. DENTAL CARE: Maintain your pet’s dental hygiene by brushing their teeth or providing dental treats.",
        "9. PREVENT PARASITES: Use preventive treatments to protect your pet from fleas, ticks, and worms.",
        "10. MENTAL STIMULATION: Provide toys and activities to keep your pet mentally stimulated and engaged."
    )

    val gifs = listOf(
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExcDJ0ZHR4ZG40ZDV4NWg2ZGRhMmlna3VwNXo2dGp6d2p2bmxuaWU0MCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/NpGgnGZgOHcRvj9fHe/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExNmIxNWM0bXczaGRtbWxuZm8wZWlzaXY0NzIzbGN1MWg3NWdoM29hOCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/LU4h8W0oBaR7tEKs5K/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExNzFwbTRkdWdpNG50MzNvNnk3YmNzdzlscmtqbXp6bjV6cXR2dnRjOSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/Tu1p1x4QwnZAc/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExdnZtdmw2MWI1N2ZoZm5hbDZlcTV0cjRpaXNrcWd1MXc2MDhqZGx3biZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/TF6FLfa5NryGdEJcfY/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExZXdubXNhNnN2ZTh1aW01MzMyNDdkeGMzbno1OW56ZWlvYWFqMmtrZyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/13VfgGFlFM1oxa/giphy-downsized-large.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExNGVqeHIwcjZqdjJ4NmlyZmdtYnhmdnNrOHhtd3RmeXJkODI2MHpjZSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/26tPjh5FzTLPI5wcw/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExYzNhOWpucjM3YnRqMjRzNWhienowMzkwNHFrdWMxOHl4MmloNWN1cCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/hor7W22LilvnttSTAl/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExdW53c2xhMTJ3NzJhbTI0aThwOTBkb3F1d2dicTFrejE3aDdrbzc3MyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/xT9IgpwOQfx9WmFxN6/giphy-downsized-large.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExa2xkczVvcDA5MXV4b3h1NHJybWRwcm9hNXVkdmwxNDhqdm45eWVsaCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/3oEjHJgmOnewNLCCw8/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExMnFqdTdnazN5YmkxNXIwaTZtaHY5bHFoNTc4ajUzN3drZHRxMmczMyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/ffXuoiLiu1fquv10LH/giphy.gif"
    )

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "General Pet Care Tips") },
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
            // Remember the state for the pager
            val pagerState = rememberPagerState()

            // Horizontal pager to swipe through pet care tips
            HorizontalPager(
                count = tips.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) { page ->
                // Main content container for each page
                Box(modifier = Modifier.fillMaxSize()) {
                    // Background image
                    Image(
                        painter = painterResource(id = R.drawable.bg),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    // Column layout to display tip content
                    Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                        // Display GIF for the current page
                    GlideImage(
                        imageModel = gifs[page],
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                        // Display the pet care tip text
                    Spacer(modifier = Modifier.height(16.dp))
                        Card(modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.medium){
                            Text(
                                text = tips[page],
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 18.sp, color = Color(0xFFff1493)
                                ),
                                fontWeight = FontWeight.Bold
                            )
                        }
                        // Instruction to swipe left or right
                    Text(
                        text = "Swipe Left/Right :)",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(8.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}