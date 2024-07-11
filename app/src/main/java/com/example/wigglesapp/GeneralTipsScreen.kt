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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun GeneralTipsScreen(navController: NavController){
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
        "https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExNWMyN2lxMjUyMDhuejkwdDV2MHpkY3Z0bXBueXE2cHd3Y3hneWVxZiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/NpGgnGZgOHcRvj9fHe/giphy.webp",
        "https://media4.giphy.com/media/v1.Y2lkPTc5MGI3NjExcnNraThwYzB4eHNqNWM0dm1kN3FzNmNyZzF3NnJ5ZHRkeG9mbTFhcCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/3owypgpljxXcec90UU/giphy.webp",
        "https://media3.giphy.com/media/v1.Y2lkPTc5MGI3NjExZjg5dG5mbnByemh2b29sOHE4bmEzdTdqaTJmdTRiMGJ6MWQzMTJ4bSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/Tu1p1x4QwnZAc/giphy.webp",
        "https://media0.giphy.com/media/v1.Y2lkPTc5MGI3NjExeXRseXJtYzg4aWdlbzNlejA0eGR5NDN0Y2g3ejJqdW8zejM5am4zMiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/5EJHDSPpFhbG0/200.webp",
        "https://media4.giphy.com/media/v1.Y2lkPTc5MGI3NjExeDV0MnJvamQwcmhsZXltMXZxbmd1dm45eDlhZWMydTVtamJtdzVtMSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/cYZkY9HeKgofpQnOUl/giphy.webp",
        "https://media0.giphy.com/media/v1.Y2lkPTc5MGI3NjExZ2kxN3BtNjdubDVoZ2tiYTFnc29wMnl3NWl6Y3A2MGo2NndwcXM0eiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/26tPjh5FzTLPI5wcw/giphy.webp",
        "https://media3.giphy.com/media/v1.Y2lkPTc5MGI3NjExaWRua2RpYmpldGg0dHlkbXJ0dWl6NXl2MTkwdmxpZzdmZzhqNDllaiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/hor7W22LilvnttSTAl/giphy.webp",
        "https://media4.giphy.com/media/v1.Y2lkPTc5MGI3NjExbWJ5dmlza3c5MHd4Yjh1bnpnajE4dG43dTI2c3A4ajlzdHdwM2hsdyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/xT9IgpwOQfx9WmFxN6/giphy.webp",
        "https://media3.giphy.com/media/v1.Y2lkPTc5MGI3NjExamw2Nmk1MjhzMG43Z3Z2ZDBwcjdpdzJ4enNvdjR4ZTZsNGMxcDl6MCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/3oEjHJgmOnewNLCCw8/giphy.webp",
        "https://media4.giphy.com/media/v1.Y2lkPTc5MGI3NjExdG8zdml6MHkwNGQwcGpuN2sxbGpybDJxZDlrYXlqaGR5N2d6ZGJ5aiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/ffXuoiLiu1fquv10LH/giphy.webp"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "General Pet Care Tips") },
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = tips[page], fontSize = 18.sp, modifier = Modifier.padding(8.dp))
            }
        }
    }
}