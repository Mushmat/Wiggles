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
fun CatTipsScreen(navController: NavController){
    val tips = listOf(
        "1. Litter Box Maintenance: Keep the litter box clean and place it in a quiet, accessible location.",
        "2. Scratching Posts: Provide scratching posts to satisfy your cat’s natural scratching instincts.",
        "3. Regular Brushing: Brush your cat’s fur regularly to reduce shedding and prevent matting.",
        "4. Interactive Play: Engage your cat with interactive toys to keep them active and stimulated.",
        "5. Nutrition: Feed your cat high-quality cat food suitable for their age and health needs.",
        "6. Quiet Spaces: Provide quiet and safe spaces where your cat can retreat and relax.",
        "7. Routine Vet Visits: Schedule regular vet visits for checkups and vaccinations.",
        "8. Hydration: Ensure your cat has access to fresh water at all times.",
        "9. Dental Health: Maintain your cat’s dental health with regular brushing or dental treats.",
        "10. Window Perches: Install window perches to give your cat a view of the outdoors and keep them entertained."
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Cat Care Tips") },
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