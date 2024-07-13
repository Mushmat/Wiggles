import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wigglesapp.AuthViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun NavigationDrawer(navController: NavController, drawerState: DrawerState, scope: CoroutineScope,authViewModel: AuthViewModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        DrawerButton(text = "Home @ Anytime") { navController.navigate("home") }
        DrawerButton(text = "About Us") { navController.navigate("about_us_screen") }
        DrawerButton(text = "User Profile") { navController.navigate("user_profile_screen") }
        DrawerButton(text = "Bookmarked Pets") { navController.navigate("bookmarked_pets_screen") }
        DrawerButton(text = "Adoption Application Tracker") { navController.navigate("adoption_tracker") }
        DrawerButton(text = "Testimonials") { navController.navigate("testimonials_screen") }
        DrawerButton(text = "FAQs") { navController.navigate("faqs_screen") }
        DrawerButton(text = "Contact Us") { navController.navigate("contact_us_screen") }
        DrawerButton(text = "Sign Out") {authViewModel.logOut()}
        }
}

@Composable
fun DrawerButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(text = text, fontSize = 18.sp)
    }
}