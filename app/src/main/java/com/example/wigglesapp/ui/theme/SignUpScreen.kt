package com.example.wigglesapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.wigglesapp.AuthViewModel
import com.example.wigglesapp.R

@Composable
fun SignUpScreen(authViewModel: AuthViewModel, onLoginClicked: () -> Unit){
    var fullname by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val authState by authViewModel.authState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_pets_24),
                contentDescription = "Cute Pets"
            )
            Text(text = "Wiggles", style = MaterialTheme.typography.titleLarge, color = Color.Blue)

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = fullname,
                onValueChange = { fullname = it },
                label = { Text(text = "Full Name") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            TextField(
                value = dob,
                onValueChange = { dob = it },
                label = { Text(text = "Date of Birth (DD/MM/YYYY)") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            TextField(
                value = contactNumber,
                onValueChange = { contactNumber = it },
                label = { Text(text = "Contact Number") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            TextField(
                value = address,
                onValueChange = { address = it },
                label = { Text(text = "Address") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text(text = "Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                authViewModel.signUp(
                    fullname, dob, contactNumber, address, email, password, confirmPassword
                )
            }) {
                Text(text = "Create Account")
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = onLoginClicked) {
                Text(text = "Back to Login")
            }

            authState.error?.let {
                Text(text = it, color = Color.Red)
            }
        }

    }
}