package com.example.wigglesapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Composable function for creating a styled section header
@Composable
fun SectionHeader(title: String) {
    Text(
        text = title, // Text to be displayed as the section header
        fontSize = 22.sp, // Font size 22 sp
        fontWeight = FontWeight.Bold, // Bold font weight
        color = Color(0xFF6200EE), // Text color
        modifier = Modifier
            .padding(vertical = 8.dp) // Padding around the text
            .background(
                brush = Brush.horizontalGradient( // Horizontal gradient background
                    colors = listOf(Color(0xFFFFA726), Color(0xFFFF7043))
                )
            )
            .fillMaxWidth() // Text fills the width of its parent
            .padding(vertical = 8.dp, horizontal = 16.dp) // Additional padding inside the background
    )
}