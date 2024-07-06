package com.example.wigglesapp

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun NavigationDrawer(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        DrawerMenuItem(text = "About Us", icon = R.drawable.baseline_info_24) {
            navController.navigate("aboutUs")
        }
        Spacer(modifier = Modifier.height(16.dp))

        DrawerMenuItem(text = "User Profile", icon = R.drawable.person) {
            navController.navigate("userProfile")
        }
        Spacer(modifier = Modifier.height(16.dp))

        DrawerMenuItem(text = "Adoption Process Instructions", icon = R.drawable.instructions) {
            navController.navigate("adoptionProcessInstructions")
        }
        Spacer(modifier = Modifier.height(16.dp))

        DrawerMenuItem(text = "Bookmarked Pets", icon = R.drawable.bookmark) {
            navController.navigate("bookmarkedPets")
        }
        Spacer(modifier = Modifier.height(16.dp))

        DrawerMenuItem(text = "Adoption Application Tracker", icon = R.drawable.tracker) {
            navController.navigate("adoptionApplicationTracker")
        }
        Spacer(modifier = Modifier.height(16.dp))

        DrawerMenuItem(text = "Testimonials", icon = R.drawable.feedback) {
            navController.navigate("testimonials")
        }
        Spacer(modifier = Modifier.height(16.dp))

        DrawerMenuItem(text = "FAQs", icon = R.drawable.answer) {
            navController.navigate("faqs")
        }
        Spacer(modifier = Modifier.height(16.dp))

        DrawerMenuItem(text = "Contact Us", icon = R.drawable.contact) {
            navController.navigate("contactUs")
        }
        Spacer(modifier = Modifier.height(16.dp))

    }
}


@Composable
fun DrawerMenuItem(text: String, icon: Int, onClick: () -> Unit){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(painter = painterResource(id = icon), contentDescription = text, modifier = Modifier.size(48.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp))
    }
}