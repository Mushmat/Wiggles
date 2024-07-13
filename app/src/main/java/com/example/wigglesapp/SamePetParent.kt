package com.example.wigglesapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun ParentsScreen(navController: NavController){
    val parents = listOf(
        Parent(
            name = "John Doe",
            petName = "Luna",
            breed = "Golden Retriever",
            positiveTraits = "Luna is incredibly friendly, loves to play, and is great with kids. She's very loyal and has become an inseparable part of our family.",
            contactNumber = "123-456-7890",
            email = "john.doe@example.com",
            imageRes = R.drawable.john
        ),
        Parent(
            name = "Jane Smith",
            petName = "Max",
            breed = "Beagle",
            positiveTraits = "Max is very energetic and loves outdoor activities. He's very obedient and has quickly learned many commands. Great for an active family.",
            contactNumber = "234-567-8901",
            email = "jane.smith@example.com",
            imageRes = R.drawable.jane
        ),
        Parent(
            name = "Michael Johnson",
            petName = "Bella",
            breed = "Boxer",
            positiveTraits = "Bella is very protective and affectionate. She is great with children and has a calm demeanor. Perfect for a family looking for a loving companion.",
            contactNumber = "345-678-9012",
            email = "michael.johnson@example.com",
            imageRes = R.drawable.michael
        ),
        Parent(
            name = "Emily Davis",
            petName = "Charlie",
            breed = "Shih Tzu",
            positiveTraits = "Charlie is very gentle and loves cuddling. He is perfect for apartment living and gets along well with other pets. Ideal for someone looking for a low-maintenance pet.",
            contactNumber = "456-789-0123",
            email = "emily.davis@example.com",
            imageRes = R.drawable.emily
        ),
        Parent(
            name = "Sarah Brown",
            petName = "Cooper",
            breed = "Poodle",
            positiveTraits = "Cooper is highly intelligent and very easy to train. He has a playful nature and is great with kids. Perfect for a family looking for an active and smart pet.",
            contactNumber = "567-890-1234",
            email = "sarah.brown@example.com",
            imageRes = R.drawable.sarah
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Parents who bought similar pets!") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(parents) { parent ->
                ParentCard(navController = navController, parent = parent)
            }
        }
    }
}

@Composable
fun ParentCard(navController: NavController, parent: Parent) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("parent_detail/${parent.name}") }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = parent.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp)
            )
            Column {
                Text(text = parent.name, fontSize = 18.sp, color = Color.Black)
                Text(text = parent.breed, fontSize = 16.sp, color = Color.Gray)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParentDetailScreen(navController: NavController, parentName: String) {
    val parent = getParentByName(parentName)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Parent Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = parent.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 16.dp)
            )
            Text(text = "Name: ${parent.name}", fontSize = 24.sp, color = Color.Black)
            Text(text = "Pet's Name: ${parent.petName}", fontSize = 20.sp, color = Color.Gray)
            Text(text = "Breed: ${parent.breed}", fontSize = 20.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Positive Traits and Reviews:", fontSize = 20.sp, color = Color.Black)
            Text(text = parent.positiveTraits, fontSize = 18.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Contact Information:", fontSize = 20.sp, color = Color.Black)
            Text(text = "Contact Number: ${parent.contactNumber}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = "Email: ${parent.email}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }
}

fun getParentByName(name: String): Parent {
    return when (name) {
        "John Doe" -> Parent(
            name = "John Doe",
            petName = "Luna",
            breed = "Golden Retriever",
            positiveTraits = "Luna is incredibly friendly, loves to play, and is great with kids. She's very loyal and has become an inseparable part of our family.",
            contactNumber = "123-456-7890",
            email = "john.doe@example.com",
            imageRes = R.drawable.john
        )
        "Jane Smith" -> Parent(
            name = "Jane Smith",
            petName = "Max",
            breed = "Beagle",
            positiveTraits = "Max is very energetic and loves outdoor activities. He's very obedient and has quickly learned many commands. Great for an active family.",
            contactNumber = "234-567-8901",
            email = "jane.smith@example.com",
            imageRes = R.drawable.jane
        )
        "Michael Johnson" -> Parent(
            name = "Michael Johnson",
            petName = "Bella",
            breed = "Boxer",
            positiveTraits = "Bella is very protective and affectionate. She is great with children and has a calm demeanor. Perfect for a family looking for a loving companion.",
            contactNumber = "345-678-9012",
            email = "michael.johnson@example.com",
            imageRes = R.drawable.michael
        )
        "Emily Davis" -> Parent(
            name = "Emily Davis",
            petName = "Charlie",
            breed = "Shih Tzu",
            positiveTraits = "Charlie is very gentle and loves cuddling. He is perfect for apartment living and gets along well with other pets. Ideal for someone looking for a low-maintenance pet.",
            contactNumber = "456-789-0123",
            email = "emily.davis@example.com",
            imageRes = R.drawable.emily
        )
        "Sarah Brown" -> Parent(
            name = "Sarah Brown",
            petName = "Cooper",
            breed = "Poodle",
            positiveTraits = "Cooper is highly intelligent and very easy to train. He has a playful nature and is great with kids. Perfect for a family looking for an active and smart pet.",
            contactNumber = "567-890-1234",
            email = "sarah.brown@example.com",
            imageRes = R.drawable.sarah
        )
        else -> Parent(
            name = "John Doe",
            petName = "Luna",
            breed = "Golden Retriever",
            positiveTraits = "Luna is incredibly friendly, loves to play, and is great with kids. She's very loyal and has become an inseparable part of our family.",
            contactNumber = "123-456-7890",
            email = "john.doe@example.com",
            imageRes = R.drawable.john
        )
    }
}

data class Parent(
    val name: String,
    val petName: String,
    val breed: String,
    val positiveTraits: String,
    val contactNumber: String,
    val email: String,
    val imageRes: Int
)