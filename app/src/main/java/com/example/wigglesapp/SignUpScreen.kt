package com.example.wigglesapp

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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(authViewModel: AuthViewModel, onLoginClicked: () -> Unit) {
    var fullname by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var dobError by remember { mutableStateOf(false) }
    val authState by authViewModel.authState.collectAsState()

    Scaffold(
        topBar = {
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
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        ) {
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
                        .padding(vertical = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_pets_24),
                        contentDescription = "Cute Pets",
                        modifier = Modifier.size(48.dp)
                    )
                    Text(text = "Wiggles", style = MaterialTheme.typography.titleLarge, color = Color.Black, fontSize = 60.sp)

                    Spacer(modifier = Modifier.height(16.dp))

                    TextField(
                        value = fullname,
                        onValueChange = { fullname = it },
                        label = { Text(text = "Full Name") },
                        modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    TextField(
                        value = dob,
                        onValueChange = { dob = it },
                        label = { Text(text = "Date of Birth (DD/MM/YYYY)") },
                        modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    if (dobError) {
                        Text(text = "Please enter a valid Date of Birth", color = Color.Red, fontSize = 14.sp)
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    TextField(
                        value = contactNumber,
                        onValueChange = { contactNumber = it },
                        label = { Text(text = "Contact Number") },
                        modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    TextField(
                        value = address,
                        onValueChange = { address = it },
                        label = { Text(text = "Paw-ddress") },
                        modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(text = "Paw-mail") },
                        modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = "Paws-word") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    TextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text(text = "Confirm Paws-word") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    GradientButton(
                        onClick = {
                            if (isValidDateOfBirth(dob)) {
                                authViewModel.signUp(
                                    fullname, dob, contactNumber, address, email, password, confirmPassword
                                )
                            } else {
                                dobError = true
                            }
                        },
                        text = "Create Account",
                        gradient = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF8E44AD), Color(0xFF3498DB))
                        ),
                        modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    GradientButton(
                        onClick = onLoginClicked,
                        text = "Back to Login",
                        gradient = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF8E44AD), Color(0xFF3498DB))
                        ),
                        modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp)
                    )

                    authState.error?.let {
                        Text(text = it, color = Color.Red)
                    }
                }
            }
        }
    }
}

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
