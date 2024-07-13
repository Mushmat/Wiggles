package com.example.wigglesapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FAQsScreen(navController: NavController){
    val faqs = listOf(
        "What is Wiggles?" to "Wiggles is a pet adoption app designed to connect potential pet owners with pets in need of a loving home. Our mission is to make the adoption process as smooth and efficient as possible.",
        "How do I create an account on Wiggles?" to "To create an account, click on the 'Sign Up' button on the home screen and fill in the required details, such as your full name, date of birth, contact number, address, email, and password.",
        "Is there a fee for using Wiggles?" to "No, using Wiggles to find and adopt pets is completely free. However, some shelters or adoption agencies may have their own fees.",
        "How do I adopt a pet?" to "Browse the available pets, click on the one you are interested in, and then click on the 'Adopt' button. Follow the steps in the adoption application process to complete your request.",
        "What information is required for the adoption application?" to "You will need to provide information about the pet owner, such as their name, gender, age, email, assistive services needed, pet allowance, pet history, time allowance, travel habits, and request hold time.",
        "How long does the adoption process take?" to "The adoption process timeline varies depending on the shelter or adoption agency. You will be notified via email about the status of your application.",
        "Where can I find pet care tips?" to "You can find pet care tips by clicking on the 'Pet Care' section in the app, where you will find general tips, dog-specific tips, and cat-specific tips.",
        "How do I find a vet near me?" to "Click on the 'Vets nearby' button in the 'Pet Care' section, and you will be redirected to Google Maps with a search for nearby vets.",
        "What should I do if I encounter a technical issue?" to "If you encounter any technical issues, please contact our support team at contact@wigglesapp.com or call us at 8114489928. You can also fill out our Google Form for further assistance.",
        "How can I reset my password?" to "To reset your password, click on the 'Forgot Password' link on the login screen, enter your registered email address, and follow the instructions sent to your email."
    )

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "FAQs") },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "BACK")
                    }
                }
            )
        }
    ){ paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(faqs) { faq ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.Start
                    ){
                        Text(
                            text = faq.first,
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp, color = Color.Black),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = faq.second,
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp,color = Color.Gray)
                        )
                    }
                }
            }
        }
    }
}