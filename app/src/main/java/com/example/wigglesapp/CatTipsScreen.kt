package com.example.wigglesapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.glide.GlideImage

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
                title = { Text(text = "Dog Care Tips") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "Back")
                    }
                }
            )
        }
    ){ paddingValues ->
        val pagerState = rememberPagerState()

        HorizontalPager(
            count = tips.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { page->
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
                Text(text = "Swipe Left/Right :)",fontSize = 18.sp, modifier = Modifier.padding(8.dp))
            }
        }
    }
}