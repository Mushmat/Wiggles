package com.example.wigglesapp.utils

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wigglesapp.ui.screens.AuthScreen
import com.example.wigglesapp.viewmodels.AuthViewModel
import com.example.wigglesapp.ui.screens.ParentDetailScreen
import com.example.wigglesapp.ui.screens.ParentsScreen
import com.example.wigglesapp.viewmodels.SharedViewModel
import com.example.wigglesapp.ui.screens.AboutUsScreen
import com.example.wigglesapp.ui.screens.AdoptionApplicationScreen
import com.example.wigglesapp.ui.screens.AdoptionSuccessScreen
import com.example.wigglesapp.ui.screens.AdoptionTrackerScreen
import com.example.wigglesapp.ui.screens.ApplicationDetailScreen
import com.example.wigglesapp.ui.screens.AvailablePetsScreen
import com.example.wigglesapp.ui.screens.BookmarkedPetsScreen
import com.example.wigglesapp.ui.screens.CatTipsScreen
import com.example.wigglesapp.ui.screens.ContactUsScreen
import com.example.wigglesapp.ui.screens.DogTipsScreen
import com.example.wigglesapp.ui.screens.FAQsScreen
import com.example.wigglesapp.ui.screens.FilterScreen
import com.example.wigglesapp.ui.screens.GeneralTipsScreen
import com.example.wigglesapp.ui.screens.HomeScreen
import com.example.wigglesapp.ui.screens.NavigationDrawer
import com.example.wigglesapp.ui.screens.PetCareOptions
import com.example.wigglesapp.ui.screens.PetCareScreen
import com.example.wigglesapp.ui.screens.PetDetailScreen
import com.example.wigglesapp.ui.screens.PetQuizScreen
import com.example.wigglesapp.ui.screens.ShelterInfoScreen
import com.example.wigglesapp.ui.screens.SuggestedPetsScreen
import com.example.wigglesapp.ui.screens.TestimonialsScreen
import com.example.wigglesapp.ui.screens.UserProfileScreen
import com.example.wigglesapp.ui.screens.dummyPets

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
                modifier = Modifier.fillMaxSize(0.80f)
            ) {
                NavigationDrawer(navController, authViewModel, drawerState, scope)
            }
        },
        content = {
            Scaffold(
                content = {
                    NavHost(
                        navController = navController,
                        startDestination = if (authState.isAuthenticated) "home" else "auth"
                    ) {
                        composable("auth") { AuthScreen(navController, authViewModel) }
                        composable("home") {
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
                        composable("bookmarked_pets_screen") {
                            BookmarkedPetsScreen(
                                navController = navController,
                                sharedViewModel = sharedViewModel
                            )
                        }
                        composable("testimonials_screen") {
                            TestimonialsScreen(
                                navController = navController
                            )
                        }
                        composable("faqs_screen") {
                            FAQsScreen(
                                navController = navController
                            )
                        }
                        composable("contact_us_screen") {
                            ContactUsScreen(
                                navController = navController
                            )
                        }
                        composable("available_pets") {
                            AvailablePetsScreen(navController = navController, filteredPets)
                        }

                        composable("pet_detail/{petId}") { backStackEntry ->
                            val petId = backStackEntry.arguments?.getString("petId")?.toInt()
                                ?: return@composable
                            PetDetailScreen(navController = navController, petId = petId, sharedViewModel = sharedViewModel)
                        }
                        composable("filter") {
                            FilterScreen(navController = navController, applyFilters = ::applyFilters)
                        }
                        composable("pet_quiz") {
                            PetQuizScreen(navController = navController, sharedViewModel = sharedViewModel)
                        }
                        composable("suggested_pets_screen") {
                            SuggestedPetsScreen(navController = navController, sharedViewModel = sharedViewModel)
                        }

                        composable("pet_care_screen") {
                            PetCareScreen(navController = navController)
                        }
                        composable("pet_care_tips_screen") {
                            PetCareOptions(navController = navController)
                        }
                        composable("general_tips") {
                            GeneralTipsScreen(navController = navController)
                        }
                        composable("dog_tips") {
                            DogTipsScreen(navController = navController)
                        }
                        composable("cat_tips") {
                            CatTipsScreen(navController = navController)
                        }
                        composable("shelter_info") {
                            ShelterInfoScreen(navController = navController)
                        }
                        composable("adoption_application/{petId}") { backStackEntry ->
                            val petId = backStackEntry.arguments?.getString("petId")?.toInt() ?: return@composable
                            AdoptionApplicationScreen(navController = navController, petId = petId, sharedViewModel = sharedViewModel)
                        }
                        composable("adoption_success") {
                            AdoptionSuccessScreen(navController = navController)
                        }
                        composable("adoption_tracker") {
                            AdoptionTrackerScreen(
                                navController = navController,
                                sharedViewModel = sharedViewModel
                            )
                        }
                        composable("application_detail/{petId}") { backStackEntry ->
                            val petId = backStackEntry.arguments?.getString("petId")?.toInt() ?: return@composable
                            ApplicationDetailScreen(navController = navController, petId = petId, sharedViewModel = sharedViewModel)
                        }

                        composable("parent_detail/{parentName}") { backStackEntry ->
                            val parentName = backStackEntry.arguments?.getString("parentName") ?: return@composable
                            ParentDetailScreen(navController = navController, parentName = parentName)
                        }

                        composable("parents_screen") {
                            ParentsScreen(navController = navController)
                        }
                    }
                }
            )
        }
    )
}
