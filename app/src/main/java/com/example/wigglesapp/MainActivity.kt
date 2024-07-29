package com.example.wigglesapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.example.wigglesapp.utils.MyApp
import com.example.wigglesapp.utils.NotificationUtils
import com.example.wigglesapp.viewmodels.AuthState
import com.example.wigglesapp.viewmodels.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()

    private val requestNotificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission is granted
            NotificationUtils.createNotificationChannel(this)
        } else {
            // Permission is denied
            // Handle the denial case if necessary
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(authViewModel)
        }

        // Check for the current user and set auth state
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            authViewModel.resetAuthState()
            authViewModel._authState.value = AuthState(isAuthenticated = true)
        }

        // Create notification channel for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationUtils.createNotificationChannel(this)
        }

        // Request notification permission for Android 13 and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // Permission is already granted
                    NotificationUtils.createNotificationChannel(this)
                }
                else -> {
                    // Request permission
                    requestNotificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }
    }
}
