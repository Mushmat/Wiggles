package com.example.wigglesapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat.Type
import com.example.wigglesapp.R
import com.example.wigglesapp.ui.components.GradientButton
import com.example.wigglesapp.viewmodels.AuthViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(authViewModel: AuthViewModel, onLoginClicked: () -> Unit) {
    // State variables to hold user input
    var fullname by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var dobError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    val authState by authViewModel.authState.collectAsState()

    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // Getting the current view to calculate insets
    val view = LocalView.current
    val density = LocalDensity.current

    // Get the current window insets to determine if the keyboard is visible
    val imeHeightPx = ViewCompat.getRootWindowInsets(view)?.getInsets(Type.ime())?.bottom ?: 0

    // Convert the height from pixels to DP
    val imeHeightDp = with(density) { imeHeightPx.toDp() }

    Scaffold(
        topBar = {
            // Top AppBar with title and back navigation
            TopAppBar(
                title = { Text(text = "Create your Paw-Profile") },
                navigationIcon = {
                    IconButton(onClick = onLoginClicked) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Background image
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp)
                        .padding(bottom = imeHeightDp), // Adjust bottom padding based on keyboard height
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        // App logo and title
                        painter = painterResource(id = R.drawable.baseline_pets_24),
                        contentDescription = "Cute Pets",
                        modifier = Modifier.size(48.dp)
                    )
                    Text(
                        text = "Wiggles",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black,
                        fontSize = 60.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Full name input field
                    TextField(
                        value = fullname,
                        onValueChange = { fullname = it },
                        label = { Text(text = "Full Name") },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // Date of birth input field
                    TextField(
                        value = dob,
                        onValueChange = { dob = it },
                        label = { Text(text = "Date of Birth (DD/MM/YYYY)") },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    if (dobError) {
                        Text(
                            text = "Please enter a valid Date of Birth",
                            color = Color.Red,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Contact number input field
                    TextField(
                        value = contactNumber,
                        onValueChange = { contactNumber = it },
                        label = { Text(text = "Contact Number") },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    // Address input field
                    TextField(
                        value = address,
                        onValueChange = { address = it },
                        label = { Text(text = "Paw-ddress") },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    // Email input field
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(text = "Paw-mail") },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    // Password input field
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = "Paws-word") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    if (passwordError) {
                        Text(
                            text = "Password must be at least 7 characters",
                            color = Color.Red,
                            fontSize = 14.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))

                    // Confirm password input field
                    TextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text(text = "Confirm Paws-word") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Create Account button
                    GradientButton(
                        onClick = {
                            dobError = !isValidDateOfBirth(dob)
                            passwordError = password.length < 6
                            if (!dobError && !passwordError) {
                                isLoading = true
                                scope.launch {
                                    delay(5000)
                                    authViewModel.signUp(
                                        fullname,
                                        dob,
                                        contactNumber,
                                        address,
                                        email,
                                        password,
                                        confirmPassword
                                    )
                                }
                            }
                        },
                        text = "Create Account",
                        gradient = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF8E44AD), Color(0xFF3498DB))
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Back to Login button
                    GradientButton(
                        onClick = onLoginClicked,
                        text = "Back to Login",
                        gradient = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF8E44AD), Color(0xFF3498DB))
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp)
                    )

                    // Display error message if there is an error in authentication state
                    authState.error?.let {
                        Text(text = it, color = Color.Red)
                    }
                }
            }
            if (isLoading) {
                LoadingxScreen()
            }
        }
    }
}

@Composable
fun LoadingxScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(Color.White.copy(alpha = 0.8f))
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .padding(24.dp)
        ) {
            CircularProgressIndicator(
                color = Color(0xFF3498DB),
                strokeWidth = 6.dp,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.animated_dog_image), // Replace with your dog image resource
                contentDescription = "Loading Dog",
                modifier = Modifier.size(128.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Loading... Get ready for some pawsome content!",
                fontSize = 18.sp,
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

// Function to validate the date of birth
fun isValidDateOfBirth(dob: String): Boolean {
    return try {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        sdf.isLenient = false
        val date = sdf.parse(dob)
        val calendar = Calendar.getInstance()
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        year in 1900..Calendar.getInstance().get(Calendar.YEAR) && Calendar.getInstance().get(Calendar.YEAR) - year >= 18
    } catch (e: Exception) {
        false
    }
}
