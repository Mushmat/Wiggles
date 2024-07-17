package com.example.wigglesapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Composable function for creating a custom-styled button used in a drawer menu
@Composable
fun DrawerButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick, // Action to be performed on button click
        modifier = Modifier
            .fillMaxWidth() // Button fills the width of its parent
            .padding(vertical = 8.dp) // Padding around the button
            .clip(RoundedCornerShape(12.dp)) // Rounded corners with a radius of 12 dp
            .background(
                brush = Brush.horizontalGradient( // Horizontal gradient background
                    listOf(Color(0xFFFFC1E3), Color(0xFFFF8A80)) // Gradient colors
                )
            )
            .shadow(8.dp, RoundedCornerShape(12.dp)), // Shadow with rounded corners and elevation of 8 dp
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent) // Transparent button container color
    ) {
        Text(text = text, fontSize = 18.sp, color = Color.Black) // Button text with size 18 sp and black color
    }
}