import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun NavigationDrawer(navController: NavController, drawerState: DrawerState, scope: CoroutineScope) {
    Column(modifier = Modifier.padding(16.dp)) {
        DrawerButton(text = "About Us") { /* Navigate to About Us */ }
        DrawerButton(text = "User Profile") { /* Navigate to User Profile */ }
        DrawerButton(text = "Adoption Process Instructions") { /* Navigate to Adoption Process Instructions */ }
        DrawerButton(text = "Bookmarked Pets") { /* Navigate to Bookmarked Pets */ }
        DrawerButton(text = "Adoption Application Tracker") { /* Navigate to Adoption Application Tracker */ }
        DrawerButton(text = "Testimonials") { /* Navigate to Testimonials */ }
        DrawerButton(text = "FAQs") { /* Navigate to FAQs */ }
        DrawerButton(text = "Contact Us") { /* Navigate to Contact Us */ }
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