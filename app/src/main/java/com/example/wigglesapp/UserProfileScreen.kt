package com.example.wigglesapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(navController: NavController, authViewModel: AuthViewModel){
    val authState by authViewModel.authState.collectAsState()

    var fullname by remember { mutableStateOf(authState.user?.fullName ?: "") }
    var contactNumber by remember { mutableStateOf(authState.user?.contactNumber ?: "") }
    var address by remember { mutableStateOf(authState.user?.address ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "User Profile") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = fullname,
                onValueChange = {fullname = it},
                label = { Text(text = "Full Name")},
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.height(6.dp))

            TextField(
                value = contactNumber,
                onValueChange = { contactNumber = it },
                label = { Text(text = "Contact Number") },
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            TextField(
                value = address,
                onValueChange = { address = it },
                label = { Text(text = "Address") },
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            TextField(
                value = authState.user?.dob ?: "",
                onValueChange = {},
                label = { Text(text = "Date of Birth (DD/MM/YYYY)") },
                enabled = false,
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    disabledLabelColor = Color.Gray,
                    disabledTextColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            TextField(
                value = authState.user?.email ?: "",
                onValueChange = {},
                label = { Text(text = "Email") },
                enabled = false,
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    disabledLabelColor = Color.Gray,
                    disabledTextColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                authViewModel.updateUserProfile(
                    fullname, contactNumber, address
                )
            }) {
                Text(text = "Update Profile", fontSize = 18.sp)
            }
        }
    }

}