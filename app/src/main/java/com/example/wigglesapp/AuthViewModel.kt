package com.example.wigglesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    internal val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState

    fun signUp(
        fullName: String, dob: String, contactNumber: String,
        address: String, email: String, password: String, confirmPassword: String
    ){
        viewModelScope.launch {
            if(password != confirmPassword){
                _authState.value = _authState.value.copy(error = "Passwords do not match!")
                return@launch
            }

            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    val userId = firebaseAuth.currentUser?.uid ?: ""
                    val user = User(fullName, dob, contactNumber, address, email)
                    firestore.collection("users").document(userId).set(user)
                        .addOnSuccessListener {
                            _authState.value = _authState.value.copy(isAuthenticated = true, user = user)
                        }
                        .addOnFailureListener {
                            _authState.value = _authState.value.copy(error = "Failed to save user data")
                        }
                } else {
                    _authState.value = _authState.value.copy(error = task.exception?.message ?: "Sign up failed!")
                }
            }
        }
    }

    fun logIn(email: String, password: String){
        viewModelScope.launch {
            firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val userId = firebaseAuth.currentUser?.uid ?: ""
                        firestore.collection("users").document(userId).get()
                            .addOnSuccessListener { document ->
                                if (document != null) {
                                    val user = document.toObject(User::class.java)
                                    _authState.value = _authState.value.copy(isAuthenticated = true, user = user)
                                } else {
                                    _authState.value = _authState.value.copy(error = "User data not found")
                                }
                            }
                            .addOnFailureListener {
                                _authState.value = _authState.value.copy(error = "Failed to fetch user data")
                            }
                    } else {
                        _authState.value = _authState.value.copy(error = task.exception?.message ?: "Login Failed")
                    }
                }
        }
    }

    fun logOut(){
        viewModelScope.launch {
            firebaseAuth.signOut()
            _authState.value = AuthState(isAuthenticated = false)
        }
    }

    fun updateUserProfile(fullName: String, contactNumber: String, address: String) {
        viewModelScope.launch {
            val currentUser = firebaseAuth.currentUser
            if (currentUser != null) {
                val userId = currentUser.uid
                val userUpdates = mapOf(
                    "fullName" to fullName,
                    "contactNumber" to contactNumber,
                    "address" to address
                )
                firestore.collection("users").document(userId).update(userUpdates)
                    .addOnSuccessListener {
                        _authState.value.user?.let { user ->
                            _authState.value = _authState.value.copy(user = user.copy(fullName = fullName, contactNumber = contactNumber, address = address))
                        }
                    }
                    .addOnFailureListener {
                        _authState.value = _authState.value.copy(error = "Failed to update user data")
                    }
            }
        }
    }

    fun resetAuthState(){
        _authState.value = AuthState()
    }
}

data class AuthState(
    val isAuthenticated: Boolean = false,
    val error: String? = null,
    val user: User? = null
)

data class User(
    val fullName: String = "",
    val dob: String = "",
    val contactNumber: String = "",
    val address: String = "",
    val email: String = ""
)
