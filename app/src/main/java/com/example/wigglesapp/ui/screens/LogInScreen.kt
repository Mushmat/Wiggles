package com.example.wigglesapp.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wigglesapp.viewmodels.AuthViewModel
import com.example.wigglesapp.R
import com.example.wigglesapp.ui.components.GradientButton
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(authViewModel: AuthViewModel, onSignUpClicked: () -> Unit) {
    // State variables to manage input fields and errors
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    val authState by authViewModel.authState.collectAsState()
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        // Column layout to display login form
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            // Logo and title
            Image(
                painter = painterResource(id = R.drawable.baseline_pets_24),
                contentDescription = "Cute Pets",
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = "Wiggles",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                fontSize = 60.sp,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Email input field
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Paw-mail") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp)
            )
            if (emailError.isNotEmpty()) {
                Text(text = emailError, color = Color.Red, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Password input field
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Paws-word") },
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp)
            )
            if (passwordError.isNotEmpty()) {
                Text(text = passwordError, color = Color.Red, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Login button
            GradientButton(
                onClick = {
                    if (validateFields(email, password, { emailError = it }, { passwordError = it })) {
                        authViewModel.logIn(email, password)
                    }
                },

                text = "Paws In",
                gradient = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF8E44AD), Color(0xFF3498DB))
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Sign up button
            GradientButton(
                onClick = onSignUpClicked,
                text = "New to Wiggles?",
                gradient = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF8E44AD), Color(0xFF3498DB))
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(8.dp)
            )

            // Forgot password text
            Text(
                text = "Forgot Password?",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { sendPasswordResetEmail(email, context) }
            )

            // Display authentication error if any
            authState.error?.let {
                Text(text = it, color = Color.Red)
            }
        }
    }
}

// Function to validate input fields
fun validateFields(email: String, password: String, onEmailError: (String) -> Unit, onPasswordError: (String) -> Unit): Boolean {
    var isValid = true

    if (email.isEmpty()) {
        onEmailError("Email cannot be empty")
        isValid = false
    } else {
        onEmailError("")
    }

    if (password.isEmpty()) {
        onPasswordError("Password cannot be empty")
        isValid = false
    } else {
        onPasswordError("")
    }

    return isValid
}

// Function to send a password reset email
fun sendPasswordResetEmail(email: String, context: Context) {
    if (email.isNotEmpty()) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Password reset email sent.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    } else {
        Toast.makeText(context, "Please enter your email.", Toast.LENGTH_SHORT).show()
    }
}