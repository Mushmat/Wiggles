package com.example.wigglesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun DogTipsScreen(navController: NavController) {
    val tips = listOf(
        "1. SOCIALIZATION: Expose your dog to different people, environments, and other animals.",
        "2. REGULAR WALKS: Take your dog for daily walks to provide physical exercise and mental stimulation.",
        "3. OBEDIENCE TRAINING: Teach your dog basic obedience commands and good manners.",
        "4. GROOMING: Regularly bathe and brush your dog to keep their coat clean and healthy.",
        "5. NUTRITION: Feed your dog high-quality dog food appropriate for their age and activity level.",
        "6. SAFE TOYS: Provide safe chew toys to keep your dog entertained and to prevent destructive chewing.",
        "7. COMFORTABLE BED: Ensure your dog has a comfortable place to sleep and rest.",
        "8. ROUTINE CHECKUPS: Take your dog to the vet for routine checkups and vaccinations.",
        "9. AVOID OVERFEEDING: Monitor your dog’s diet and avoid overfeeding to prevent obesity."
    )

    val gifs = listOf(
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExazF5c3U1eGxobjd2Y202MzB5Y2ZrcHRoMWlubjQ2aTNleHNxbHMwdyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/mokQK7oyiR8Sk/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExajZvZjBhdm9qdjY0d29lMzd2cGJyM2gxbnI3c25hdnA2dWwzY2FvaSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/fxOcPNZkLa68iQ52l9/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExbWYybXFyaTBtYnJtNnVwZXFldGtsamF4Mm1rcGgzbjRnNTluMmc1byZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/L3urg9t8mPE4IG7VF4/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExZm5ldnZ0eWZ5ZWI5eHpzb3A2cTBldm41ejZzNHhnM2prNGR0cTdodiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/3o7buc7sAR6NEfAzTi/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExYXZqOXd2YWJobTBydjkwdHRjandkeG12cmxteHA4cjFoOXV3ZDd3bCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/U9yF7jHsSRxQaOADsq/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExejRmb3o3cjYzbWdyN3VmcW16MzZ5ejJ2YWZ5dXgzdzFuNWZwczNvMCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/vq7R0XFCHxwqs0hN3f/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExdWlmbjBiZmJvNnBqYjJvZW85eGpscHVtZHRxeDFibDY1cGpmbHZyaSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/l0HlzpiDh3uE2dB72/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExdzZqY21hNHo3aGtjaGZyd24wOHdsYzgzZmc3ZXgybzNrcXJka2JlZyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/H4XImflNDhzByzGDkl/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExMWlyaW9wN3Fvemw0Mm5lODltZWNtOGc0ZjM1N2Y2NHpiNHkwMGtoMCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/rzDWq0xhY7X5S/giphy.gif"
    )

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Care the Dogs") },
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
            val pagerState = rememberPagerState()

            HorizontalPager(
                count = tips.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) { page ->
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
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GlideImage(
                        imageModel = gifs[page],
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = tips[page], fontSize = 18.sp, modifier = Modifier.padding(8.dp))
                    Text(
                        text = "Swipe Left/Right :)",
                        fontSize = 18.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}