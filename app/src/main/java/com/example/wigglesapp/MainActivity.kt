package com.example.wigglesapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(authViewModel)
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
            NavigationDrawer(navController, drawerState, scope)
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
                    composable("home") { HomeScreen(navController, drawerState, scope) }
                }
            }
        }
    )
}
