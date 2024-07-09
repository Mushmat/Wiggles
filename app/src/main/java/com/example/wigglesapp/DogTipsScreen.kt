package com.example.wigglesapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogTipsScreen(navController: NavController){
    val tips = listOf(
        "Socialization: Expose your dog to different people, environments, and other animals.",
        "Regular Walks: Take your dog for daily walks to provide physical exercise and mental stimulation.",
        "Obedience Training: Teach your dog basic obedience commands and good manners.",
        "Grooming: Regularly bathe and brush your dog to keep their coat clean and healthy.",
        "Nutrition: Feed your dog high-quality dog food appropriate for their age and activity level.",
        "Safe Toys: Provide safe chew toys to keep your dog entertained and to prevent destructive chewing.",
        "Comfortable Bed: Ensure your dog has a comfortable place to sleep and rest.",
        "Routine Checkups: Take your dog to the vet for routine checkups and vaccinations.",
        "Avoid Overfeeding: Monitor your dog’s diet and avoid overfeeding to prevent obesity."
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Dog Care Tips") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(tips) { tip ->
                Text(text = tip, fontSize = 18.sp, modifier = Modifier.padding(8.dp))
            }
        }
    }
}
