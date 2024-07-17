package com.example.wigglesapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Composable function for creating a gradient-styled button
@Composable
fun GradientButton(
    onClick: () -> Unit, // Action to be performed on button click
    text: String, // Text to be displayed on the button
    gradient: Brush, // Gradient brush for the button background
    modifier: Modifier = Modifier // Optional modifier for the button
) {
    Button(
        onClick = onClick, // Handle button click event
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent // Transparent button container color
        ),
        modifier = modifier
            .clip(RoundedCornerShape(8.dp)) // Rounded corners with a radius of 8 dp
            .background(gradient) // Apply the gradient background
    ) {
        Box(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp) // Padding around the button content
        ) {
            Text(
                text = text, // Display the provided text
                fontSize = 18.sp, // Text size 18 sp
                color = Color.White // Text color white
            )
        }
    }
}