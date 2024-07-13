package com.example.wigglesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val auth = FirebaseAuth.getInstance()
    private val db = AppDatabase.getDatabase(application)
    private val userProfileDao = db.userProfileDao()

    val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> get() = _authState

    private val _userDetails = MutableStateFlow<UserProfile?>(null)
    val userDetails: StateFlow<UserProfile?> get() = _userDetails

    init {
        auth.currentUser?.let {
            viewModelScope.launch {
                val userProfile = userProfileDao.getUserProfile(it.email!!)
                _userDetails.value = userProfile
            }
        }
    }

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

    fun logOut() {
        auth.signOut()
        _authState.value = AuthState()
        _userDetails.value = null
    }

    fun resetAuthState() {
        _authState.value = AuthState()
    }

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
