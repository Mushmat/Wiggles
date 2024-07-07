package com.example.wigglesapp

import NavigationDrawer
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val authState by authViewModel.authState.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.fillMaxSize(0.85f)
            ) {
                NavigationDrawer(navController, drawerState, scope)
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Wiggles") },
                        actions = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_menu_24),
                                    contentDescription = "Menu"
                                )
                            }
                        }
                    )
                }
            ) {
                NavHost(navController = navController, startDestination = if (authState.isAuthenticated) "home" else "auth") {
                    composable("auth") { AuthScreen(navController, authViewModel) }
                    composable("home") { HomeScreen(navController, drawerState, scope, authViewModel) }
                    composable("about_us_screen"){ AboutUsScreen(
                        navController = navController,
                        drawerState = drawerState,
                        scope = scope
                    )}
                    composable("user_profile_screen"){ UserProfileScreen(
                        navController = navController,
                        drawerState = drawerState,
                        scope = scope
                    ) }
                    composable("adoption_process_screen"){ AdoptionProcessScreen(
                        navController = navController,
                        drawerState = drawerState,
                        scope = scope
                    ) }
                    composable("bookmarked_pets_screen"){ BookmarkedPetsScreen(
                        navController = navController,
                        drawerState = drawerState,
                        scope = scope
                    ) }
                    composable("adoption_application_screen"){ AdoptionApplicationScreen(
                        navController = navController,
                        drawerState = drawerState,
                        scope = scope
                    ) }
                    composable("testimonials_screen"){ TestimonialsScreen(
                        navController = navController,
                        drawerState = drawerState,
                        scope = scope
                    ) }
                    composable("faqs_screen"){ FAQsScreen(
                        navController = navController,
                        drawerState = drawerState,
                        scope = scope
                    ) }
                    composable("contact_us_screen"){ ContactUsScreen(
                        navController = navController,
                        drawerState = drawerState,
                        scope = scope
                    ) }

                }
            }
        }
    )
}
