package com.example.wigglesapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TutorialButton(
    text: String,
    icon: Int,
    onClick: () -> Unit,
    highlight: Boolean,
    onGloballyPositioned: (LayoutCoordinates) -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                brush = Brush.horizontalGradient( // Horizontal gradient background
                    listOf(Color(0xFFFFC1E3), Color(0xFFFF8A80)) // Gradient colors
                )
            )
            .then(
                if (highlight) Modifier.border(2.dp, Color.Red, RoundedCornerShape(12.dp))
                else Modifier
            )
            .onGloballyPositioned { coordinates ->
                onGloballyPositioned(coordinates)
            },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White, shape = CircleShape)
                    .padding(8.dp)
            ) {
                Image(painter = painterResource(id = icon), contentDescription = null, modifier = Modifier.fillMaxSize())
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, fontSize = 18.sp, color = Color.Black)
        }
    }
}
