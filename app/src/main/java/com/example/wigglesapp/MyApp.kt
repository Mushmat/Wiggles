// MyApp.kt
package com.example.wigglesapp

import NavigationDrawer
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val authState by authViewModel.authState.collectAsState()

    val sharedViewModel: SharedViewModel = viewModel()

    var filteredPets by remember { mutableStateOf(dummyPets) }

    fun applyFilters(breeds: List<String>, genders: List<String>, sizes: List<String>) {
        filteredPets = dummyPets.filter { pet ->
            (breeds.isEmpty() || breeds.contains(pet.breed)) &&
                    (genders.isEmpty() || genders.contains(pet.gender)) &&
                    (sizes.isEmpty() || sizes.contains(pet.size))
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.fillMaxSize(0.90f)
            ) {
                NavigationDrawer(navController, drawerState, scope,authViewModel)
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
                NavHost(
                    navController = navController,
                    startDestination = if (authState.isAuthenticated) "home" else "auth"
                ) {
                    composable("auth") { AuthScreen(navController, authViewModel) }
                    composable("home"){
                        HomeScreen(
                            navController,
                            drawerState,
                            scope,
                            authViewModel
                        )
                    }
                    composable("about_us_screen") {
                        AboutUsScreen(
                            navController = navController
                        )
                    }
                    composable("user_profile_screen") {
                        UserProfileScreen(
                            navController = navController,
                            authViewModel = authViewModel
                        )
                    }
                    composable("adoption_process_screen") {
                        AdoptionProcessScreen(
                            navController = navController,
                            drawerState = drawerState,
                            scope = scope
                        )
                    }
                    composable("bookmarked_pets_screen") {
                        BookmarkedPetsScreen(
                            navController = navController,
                            sharedViewModel = sharedViewModel
                        )
                    }
                    composable("adoption_application_screen") {
                        AdoptionApplicationScreen(
                            navController = navController,
                            drawerState = drawerState,
                            scope = scope
                        )
                    }
                    composable("testimonials_screen") {
                        TestimonialsScreen(
                            navController = navController,
                            drawerState = drawerState,
                            scope = scope
                        )
                    }
                    composable("faqs_screen") {
                        FAQsScreen(
                            navController = navController,
                            drawerState = drawerState,
                            scope = scope
                        )
                    }
                    composable("contact_us_screen") {
                        ContactUsScreen(
                            navController = navController,
                            drawerState = drawerState,
                            scope = scope
                        )
                    }
                    composable("available_pets") {
                        AvailablePetsScreen(navController = navController, filteredPets)
                    }

                    composable("pet_detail/{petId}") { backStackEntry ->
                        val petId = backStackEntry.arguments?.getString("petId")?.toInt()
                            ?: return@composable
                        PetDetailScreen(navController = navController, petId = petId,sharedViewModel = sharedViewModel)
                    }
                    composable("filter") {
                        FilterScreen(navController = navController, applyFilters = ::applyFilters)
                    }
                    composable("pet_quiz"){
                        PetQuizScreen(navController = navController, sharedViewModel = sharedViewModel)
                    }
                    composable("suggested_pets_screen"){
                        SuggestedPetsScreen(navController = navController, sharedViewModel = sharedViewModel)
                    }
                    
                    composable("pet_care_screen"){
                        PetCareScreen(navController = navController)
                    }
                    composable("pet_care_tips_screen"){
                        PetCareOptions(navController = navController)
                    }
                    composable("general_tips"){
                        GeneralTipsScreen(navController = navController)
                    }
                    composable("dog_tips"){
                        DogTipsScreen(navController = navController)
                    }
                    composable("cat_tips"){
                        CatTipsScreen(navController = navController)
                    }
                    composable("shelter_info"){
                        ShelterInfoScreen(navController = navController)
                    }
                    
                }
            }
        }
    )
}
