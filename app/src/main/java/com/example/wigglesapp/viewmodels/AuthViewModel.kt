package com.example.wigglesapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.wigglesapp.data.dao.AppDatabase
import com.example.wigglesapp.data.entity.UserProfile
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val auth = FirebaseAuth.getInstance()
    private val db = AppDatabase.getDatabase(application)
    private val userProfileDao = db.userProfileDao()

    // MutableStateFlow to hold the authentication state
    val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> get() = _authState

    // MutableStateFlow to hold the user details
    private val _userDetails = MutableStateFlow<UserProfile?>(null)
    val userDetails: StateFlow<UserProfile?> get() = _userDetails

    init {
        // If user is already logged in, fetch the user profile
        auth.currentUser?.let {
            viewModelScope.launch {
                val userProfile = userProfileDao.getUserProfile(it.email!!)
                _userDetails.value = userProfile
            }
        }
    }

    // Log in function
    fun logIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    viewModelScope.launch {
                        val userProfile = userProfileDao.getUserProfile(email)
                        _userDetails.value = userProfile
                        _authState.value = AuthState(isAuthenticated = true)
                    }
                } else {
                    _authState.value = AuthState(error = task.exception?.message)
                }
            }
    }

    // Sign up function
    fun signUp(fullName: String, dob: String, contactNumber: String, address: String, email: String, password: String, confirmPassword: String) {
        if (password != confirmPassword) {
            _authState.value = AuthState(error = "Passwords do not match")
            return
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userProfile = UserProfile(email, fullName, dob, contactNumber, address)
                    viewModelScope.launch {
                        userProfileDao.insertUserProfile(userProfile)
                        _userDetails.value = userProfile
                        _authState.value = AuthState(isAuthenticated = true)
                    }
                } else {
                    _authState.value = AuthState(error = task.exception?.message)
                }
            }
    }

    //Log Out Function
    fun logOut() {
        auth.signOut()
        _authState.value = AuthState()
        _userDetails.value = null
    }

    //Reset state
    fun resetAuthState() {
        _authState.value = AuthState()
    }

    //Update user profile
    fun updateUserProfile(fullName: String, contactNumber: String, address: String) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val updatedProfile = UserProfile(
                email = currentUser.email!!,
                fullName = fullName,
                dob = _userDetails.value?.dob ?: "",
                contactNumber = contactNumber,
                address = address
            )
            viewModelScope.launch {
                userProfileDao.insertUserProfile(updatedProfile)
                _userDetails.value = updatedProfile
            }
        }
    }
}


//Data classes
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
