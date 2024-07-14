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
fun CatTipsScreen(navController: NavController) {
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
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExMHQ5dzd6eHdscHcyd2Jkc2tiMW90enhzaWxuaXlibjZpNW0ybzJ4NiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/l2QE861VOZU7zQdDG/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExeWx0MHllMTNsbGQza3J6YTEwamd3N3g3cmNncmFmN2lnem42YXhiaCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/vXXZ5Q2hIzv20/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExZHlyNDZkc2M1bnV1YTViZWVhNXgyOXlxaDQ2NjJheG4zMjFvYjc3cyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/BYsKQyHGSDyo0/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExY3QyOHBvNW5sOXhuZjRreXhucmp2MTVkaW53ZDRycmhhbXJmajBxYiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/Fig1uR9DGHf6E/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExdXF3Z2w1b2RpbTBoOTEzemtmaXV6em52M3g0aHhpcnkzbnU1MTRuciZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/zLGga2ZuEeKBZjqFu9/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExaHE1aDljdXF6YmxjenIyZnAxdWNoYzlnbWJ2eTBjM2JrdWpvcmJjciZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/10zHDq77BLwcy4/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExY2xncG1zb3c5bXk4dGE2NXgxZnMzYmI4czhvazd5aHExcHRqam5nMyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/9Ai5dIk8xvBm0/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExOW1vaHJhb2VrYTRqN2tjeHY2bm56OWFuejhuaGdjczA1Y3pnMWJ5YSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/JhUZYdpnqrgcM/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExZ3Q4cXd0aXRxYTJrN3ExeW4yOXpkdGJyaGVudm81eHkwb3lsbHQ1YSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/8I7a41uPPoFSU/giphy.gif",
        "https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExeHc5OHhqcnFrcnNwazJrMXlvY3NxOHZsbmIyMmJvMnZqaTNnd2kzdyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/5UmUwfmtIqie4/giphy.gif"
    )


        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Cat Care Tips") },
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