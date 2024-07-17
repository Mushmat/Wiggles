package com.example.wigglesapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Composable function for creating a custom-styled home button with an icon and text
@Composable
fun HomeButton(text: String, icon: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick, // Action to be performed on button click
        modifier = Modifier
            .fillMaxWidth() // Button fills the width of its parent
            .padding(vertical = 8.dp) // Padding around the button
            .clip(RoundedCornerShape(12.dp)) // Rounded corners with a radius of 12 dp
            .background(
                brush = Brush.horizontalGradient( // Horizontal gradient background
                    listOf(Color(0xFF42A5F5), Color(0xFF1E88E5)) // Gradient colors
                )
            )
            .shadow(8.dp, RoundedCornerShape(12.dp)), // Shadow with rounded corners and elevation of 8 dp
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent) // Transparent button container color
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically // Align items vertically in the center
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp) // Box size for the icon background
                    .background(Color.White, shape = CircleShape) // White circular background for the icon
                    .padding(8.dp) // Padding around the icon
            ) {
                Image(painter = painterResource(id = icon), contentDescription = null, modifier = Modifier.fillMaxSize())
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, fontSize = 18.sp, color = Color.White)
        }
    }
}
