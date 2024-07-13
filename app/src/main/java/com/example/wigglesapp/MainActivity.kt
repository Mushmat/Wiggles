package com.example.wigglesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(authViewModel)
        }

        val currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser != null){
            authViewModel.resetAuthState()
            authViewModel._authState.value = AuthState(isAuthenticated = true)
        }
    }
}
