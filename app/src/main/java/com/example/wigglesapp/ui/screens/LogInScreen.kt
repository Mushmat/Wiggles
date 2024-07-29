package com.example.wigglesapp.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wigglesapp.viewmodels.AuthViewModel
import com.example.wigglesapp.viewmodels.SharedViewModel
import com.example.wigglesapp.R
import com.example.wigglesapp.ui.components.GradientButton
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    sharedViewModel: SharedViewModel,
    onSignUpClicked: () -> Unit
) {
    // State variables to manage input fields and errors
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val authState by authViewModel.authState.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

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
                        isLoading = true
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
                isLoading = false
            }

            if (authState.isAuthenticated) {
                isLoading = false
                sharedViewModel.handleUserLogin(email)
                // Navigate to the home screen or perform other actions as needed
            }
        }

        if (isLoading) {
            LoadingScreen()
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.animated_dog_image),
                contentDescription = "Loading",
                modifier = Modifier.size(128.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Hang tight! We are fetching your pet-tastic experience...",
                fontSize = 20.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator(color = Color(0xFF3498DB))

            // Adding a delay to keep the loading screen visible
            LaunchedEffect(Unit) {
                delay(10000) //seconds delay
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
