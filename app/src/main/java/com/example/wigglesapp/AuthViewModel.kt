package com.example.wigglesapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState

    private val _userDetails = MutableStateFlow<User?>(null)
    val userDetails: StateFlow<User?> = _userDetails

    fun signUp(
        fullName: String, dob: String, contactNumber: String,
        address: String, email: String, password: String, confirmPassword: String
    ) {
        viewModelScope.launch {
            if (password != confirmPassword) {
                _authState.value = _authState.value.copy(error = "Passwords do not match!")
                return@launch
            }

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = firebaseAuth.currentUser?.uid ?: ""
                    val user = User(fullName, dob, contactNumber, address, email)
                    firestore.collection("users").document(userId).set(user)
                        .addOnSuccessListener {
                            _authState.value = _authState.value.copy(isAuthenticated = true)
                            Log.d("AuthViewModel", "User data saved successfully.")
                            fetchUserDetails()
                        }
                        .addOnFailureListener { exception ->
                            _authState.value = _authState.value.copy(error = "Failed to save user data: ${exception.message}")
                            Log.e("AuthViewModel", "Failed to save user data: ${exception.message}", exception)
                        }
                } else {
                    _authState.value = _authState.value.copy(error = task.exception?.message ?: "Sign up failed!")
                    Log.e("AuthViewModel", "Sign up failed: ${task.exception?.message}", task.exception)
                }
            }
        }
    }

    fun logIn(email: String, password: String) {
        viewModelScope.launch {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _authState.value = _authState.value.copy(isAuthenticated = true)
                        Log.d("AuthViewModel", "Login successful.")
                        fetchUserDetails()
                    } else {
                        _authState.value = _authState.value.copy(error = task.exception?.message ?: "Login Failed")
                        Log.e("AuthViewModel", "Login failed: ${task.exception?.message}", task.exception)
                    }
                }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            firebaseAuth.signOut()
            _authState.value = AuthState(isAuthenticated = false)
            _userDetails.value = null
            Log.d("AuthViewModel", "Logged out.")
        }
    }

    fun resetAuthState() {
        _authState.value = AuthState()
    }

    private fun fetchUserDetails() {
        val userId = firebaseAuth.currentUser?.uid ?: return
        firestore.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                val user = document.toObject(User::class.java)
                _userDetails.value = user
                Log.d("AuthViewModel", "User data fetched successfully: $user")
            }
            .addOnFailureListener { exception ->
                _authState.value = _authState.value.copy(error = "Failed to fetch user data: ${exception.message}")
                Log.e("AuthViewModel", "Failed to fetch user data: ${exception.message}", exception)
            }
    }

    fun updateUserProfile(fullName: String, contactNumber: String, address: String) {
        val userId = firebaseAuth.currentUser?.uid ?: return
        val user = _userDetails.value ?: return

        val updatedUser = user.copy(fullName = fullName, contactNumber = contactNumber, address = address)
        firestore.collection("users").document(userId).set(updatedUser)
            .addOnSuccessListener {
                _userDetails.value = updatedUser
                _authState.value = _authState.value.copy(error = null)
                Log.d("AuthViewModel", "User data updated successfully.")
            }
            .addOnFailureListener { exception ->
                _authState.value = _authState.value.copy(error = "Failed to update user data: ${exception.message}")
                Log.e("AuthViewModel", "Failed to update user data: ${exception.message}", exception)
            }
    }
}

data class AuthState(
    val isAuthenticated: Boolean = false,
    val error: String? = null
)

data class User(
    val fullName: String = "",
    val dob: String = "",
    val contactNumber: String = "" ,
    val address: String = "",
    val email: String = ""
)
