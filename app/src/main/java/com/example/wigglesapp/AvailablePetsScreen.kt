package com.example.wigglesapp

import android.annotation.SuppressLint
import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

val dummyPets = listOf(
    Pet(1,
        "Bailey",
        "Pit Bull Terrier",
        "https://dbw3zep4prcju.cloudfront.net/animal/3cc08a4b-ffdc-47a5-bb8a-3a62147ce7cb/image/3a36eb40-c7e6-470c-9206-f5bacdcebc2b.jpg?versionId=zZ8cPVw8vhdfM6z0sSQ3EKfiBnvzIJM8&bust=1719713935&width=720",
        "Female",
        "Medium",
        "Affectionate, Funny, Quiet, Smart, Playful, Loyal, Loves, Independent, Gentle, Friendly, Dignified, Curious, Couch, Brave, Athletic",
        "Bailee was born approximately April 10th. She and her siblings found themselves in a rural shelter and in terrible condition. They were skinny, anemic, and sickly. We were able to save them thanks to foster homes. They have been thriving and are finally healthy puppies! Bailee is being fostered with her sister, and they have been doing great in their foster home!\n" +
                "\n" +
                "Bailee is thriving with crate, potty, and leash training. She has loved playing with other dogs, and has done well meeting all sorts of people. She has had a great start to basic puppy obedience training and safe puppy socialization. Bailee is already spayed. Her next vaccines are scheduled for July 8 This appointment is covered by the rescue. He is able to be adopted prior to the appointment as long as adopters are willing to get him to the appointment. "
        ),

    Pet(2,
        "Solomon",
        "Dachshund",
        "https://dbw3zep4prcju.cloudfront.net/animal/922fd600-c704-4a47-baae-d0ef5bdd7deb/image/5ddba02e-27f9-4819-93ad-5c0317fdebab.jpg?versionId=zpMokUb.EZiJrc_JdimO_kStXobV3O3w&bust=1718220692&width=720",
        "Male",
        "Small",
        "Friendly, Curious, Gentle, Affectionate, Follower, Sweet, Couch Potato",
        "Solomon has asked us to place him in a home where he can get all the attention. He is now 14 months old and is a follower. He will follow you outside. He will follow you inside. He will follow you upstairs. He will follow you downstairs. He will follow you to the bathroom and kitchen and bedroom. :) He likes to be your shadow.\n" +
                "No cats or other dogs. No children under 8 years old please.\n" +
                "Solomon enjoys walks but has no desire to meet all of the other dogs that are walking. He's a home-body and will give you lots of loves! He is a snuggler!\n" +
                "Solomon is scheduled to be neutered later in June."
        ),
    Pet(3,
        "Maple",
        "Plott Hound",
        "https://dbw3zep4prcju.cloudfront.net/animal/0d9266af-b2db-4f44-9fe0-2bfce19411b3/image/cfab1d31-a825-4454-b4a0-cf87e4ed294d.jpg?versionId=qCde0vHBgQXaybeCUS..5agtHGJ9VCcp&bust=1718220241&width=720",
        "Female",
        "Medium",
        "Friendly, Affectionate, Playful, Curious, Independent, Athletic, Cuddler, Brave",
        "Maple is about 3 months old (born mid-March).\n" +
                "From her foster: She loves everything and everyone! She is very independent and is not afraid of anything. She has gotten along very well with my dog and they love to wrestle. Maple loves to play fetch and tug. She is definitely in the teething stage and can be redirected to something appropriate to chew on.\n" +
                "She does very well in the crate. She sleeps through the night and does not go potty in the crate. Potty training is going very well, but she sometimes has small accidents when excited. Maple is great at cuddling! I call her a little space heater. :) She is perfect for those who want a play mate and a dog that will cozy up to them after a long day of work."
        ),

    Pet(4,
        "Dale",
        "Hound",
        "https://dbw3zep4prcju.cloudfront.net/animal/22e7331c-c7b9-42d2-8365-46cfe4214ad6/image/d5bde6a1-fd4c-4936-8e93-c92a4e7af37f.jpg?versionId=U.Ao8lyG0divcXiDn.duZYatJx69gsd2&bust=1717684331&width=720",
        "Male",
        "Medium",
        "Friendly, Affectionate, Playful, Smart, Brave, Curious, Funny, Athletic, Quiet, Dignified, Couch potato, Loves Kisses",
        "Meet Dale! Dale sat in a rural shelter for a while until a foster stepped up to save him. We have learned so much about him in foster care, and we can't wait for him to find his perfect home! Dale is a hound who is around 2 years old and full grown at about 35-40 pounds. Here are some notes from his foster family!\n" +
                "\n" +
                "\"Dale is the sweetest boy! He is very much a hound in some ways, but also has some characteristics that are surprising for a hound. For starters, he's pretty quiet! He doesn't have the hound bark/howl. Although we think he's around 2, he was likely never given a true puppyhood experience, and still has some puppy behaviors. These include wanting to chew things that are not toys, jumping up on you when excited, and still building confidence on the leash. All of these things are super easy to train and manage, and he is the type of pup who wants to learn! Dale is very dog friendly and great with cats as well. He would thrive with a family/person who will continue to build his basic obedience training, help him build confidence, and of course provide him with all the love and kindness he never got as a puppy.\""
        ),

    Pet(5,
        "Earl",
        "Mixed Breed",
        "https://dbw3zep4prcju.cloudfront.net/animal/5d736401-47d1-4579-8494-a67ba10a693c/image/e23e8f23-b76b-4482-b2ce-ef70362f5009.jpg?versionId=HTju8FSkvuJxIA3sMqLojqkVYMSE_Vpf&bust=1714782863&width=720",
        "Male",
        "Medium",
        "Friendly, Affectionate, Loyal, Gentle, Playful, Smart, Dignified, Quiet, Athletic, Funny, Independent, Curious, Brave, Couch potato, Loves Kisses",
        "Earl and his sister found themselves in a rural shelter just outside of Charlotte, NC. They were under one year old, and very timid and afraid. They sadly spent months there until fosters opened their homes. Earl has now been in a foster home for about a month, and he is doing amazing! We are unsure of his breed, but he is about 47 pounds and about one year old now. Here are some notes from his foster family: \n" +
                "\n" +
                "\"Earl loves other dogs and finds comfort in them. We would ideally like for him to have another dog in his forever home. He is still nervous around new people, but once he warms up to them, he is a big love bud and wants to cuddle. He has been doing wonderful with crate and potty training, and he has been amazing with all the dogs he has met. He is currently being fostered with a female dog, and they are best friends. Earl loves to play and is good at adjusting to other dogs' playstyles. He has been great around kids and cats when he's been exposed. Earl would love a home where he will be shown all the love, and bonus points if he gets to have a fur sibling!\""
        ),

    Pet(6, "Slick","Labrador Retriever","https://dbw3zep4prcju.cloudfront.net/animal/7e35e861-33a2-467d-abab-21e1e558b3c0/image/ef8895de-2c4e-4124-949c-beb5169c5914.jpeg?versionId=09HyRzi88J6E8nXBQmc4jqyC4aeyFvcq&bust=1719666397&width=720"),
    Pet(7, "Jasper","Jack Russell Terrier","https://dbw3zep4prcju.cloudfront.net/animal/4e24be5c-5309-42ff-af31-36ce6fd16de6/image/0829cfbc-d927-4e8c-a83e-398882cc4e96.jpeg?versionId=7LyfnpmQKCmQpVE3mi1lmWIfd_m8x6NX&bust=1717295973&width=720"),
    Pet(8, "Belle","Australian Cattle Dog","https://dbw3zep4prcju.cloudfront.net/animal/0efe8dfb-94ea-4cd8-9870-3ce3870f3e8d/image/45c68613-925d-47a9-a3ed-18567f407a6f.jpeg?versionId=8ODrfw.56VLA7p9ggNmIgFgUPZAJZErz&bust=1712338857&width=720"),
    Pet(9, "Alvin","Labrador Retriever","https://dbw3zep4prcju.cloudfront.net/animal/c4b66dfb-54e8-4be9-804c-1f3962a2c2b0/image/314389cf-3b5e-491c-987b-62e4b1748dda.jpeg?versionId=cRZOFJRcyASvEiqclJGqNsuVBUCdHvYS&bust=1713803507&width=720"),
    Pet(10, "Laney","German Shepherd","https://dbw3zep4prcju.cloudfront.net/animal/4738e961-5e2a-441a-9003-5aaf13ac5cb5/image/3c418608-8655-417d-a98b-c7760ac166e8.jpeg?versionId=G.JTG8rOuXw0bpLjD1y8C4tEn.9.1tL.&bust=1712335555&width=720"),

    Pet(21,"September","Domestic Short Hair","https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/72072169/1/?bust=1719985470&width=300"),
    Pet(22,"Nene","Domestic Short Hair","https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/71845070/1/?bust=1719984701&width=300"),
    Pet(23,"Asher","American Short Hair","https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/72124640/1/?bust=1720323573&width=300"),
    Pet(24,"Peugeot","Domestic Medium Hair","https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/72038617/1/?bust=1720128776&width=300"),
    Pet(25,"May","Domestic Short Hair","https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/71573001/1/?bust=1720323589&width=300"),
    Pet(26,"Lexi","Abyssinian","https://dbw3zep4prcju.cloudfront.net/animal/cd8dfafd-8299-4e82-8f32-f0a4ce2fd047/image/16f2b70e-9c88-48b4-9464-701a2487047d.jpg?versionId=LPrLMCS_4cVHpy6kc2Z7f8ux4CTfmfeh&bust=1712614228&width=300"),
    Pet(27,"Moose","Bengal","https://dbw3zep4prcju.cloudfront.net/animal/b713cfb7-234e-4870-9956-47a47ed2b682/image/bdbedbce-743a-49eb-a349-1c72e68c2ee6.jpg?versionId=7LgY6j.XmDTPMgZgRkFx1fXBvSiaFQHI&bust=1712600739&width=300"),
    Pet(28,"Chester","British Short Hair","https://dbw3zep4prcju.cloudfront.net/animal/bcc34188-453a-4d30-b813-b7d99bd2ca36/image/9cb60834-b56a-4b5c-962f-f31dd9b72291.jpg?versionId=YGMAdKfdGELGXUaMIj_uZwmEbmhkA7N3&bust=1719675861&width=300"),
    Pet(29,"Tinky","Dilute Calico","https://dbw3zep4prcju.cloudfront.net/animal/45cd62de-bcbd-4c7b-841e-71622d68bd4b/image/a588e539-0bc0-4eae-9e65-200e76e8c5c2.jpg?versionId=TsACFmj51hGkR4BuzJkc6Ns4stIiHH0j&bust=1712650429&width=300"),
    Pet(30,"Cookie","Dilute Calico","https://dbw3zep4prcju.cloudfront.net/animal/ab66eb58-776b-444e-98db-da7700c3d6d0/image/2709203a-e8e0-458a-8caa-ec4f1807cf8c.jpg?versionId=qM9OGOeicegrZDTTIUFbQew8CTuw0_qZ&bust=1718826402&width=300"),

    )

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailablePetsScreen(navController: NavController){
    val pets = remember { dummyPets }
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

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
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.background_image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Button(
                    onClick = { /*TODO HANDLE FILTERS */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Filter")
                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(pets.chunked(1)) { rowPets ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            rowPets.forEach { pet ->
                                PetCard(pet = pet)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PetCard(pet: Pet){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = pet.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
            )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = pet.name, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
        Text(text = pet.breed, fontSize = 14.sp,color = Color.White)
    }
}