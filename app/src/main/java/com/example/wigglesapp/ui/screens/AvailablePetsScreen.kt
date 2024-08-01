package com.example.wigglesapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.wigglesapp.models.Pet
import com.example.wigglesapp.R

val dummyPets = listOf(
    Pet(1, "Bailey", "Pit Bull Terrier",
        "https://dbw3zep4prcju.cloudfront.net/animal/3cc08a4b-ffdc-47a5-bb8a-3a62147ce7cb/image/3a36eb40-c7e6-470c-9206-f5bacdcebc2b.jpg",
        "Female", "Medium",
        "Low-energy, Affectionate, Quiet, Smart, Loyal, Gentle, Friendly, Curious, Couch Potato, Brave",
        "Bailee was born approximately April 10th. She and her siblings found themselves in a rural shelter and in terrible condition. They were skinny, anemic, and sickly. We were able to save them thanks to foster homes. They have been thriving and are finally healthy puppies! Bailee is being fostered with her sister, and they have been doing great in their foster home!\n" +
                "\n" +
                "Bailee is thriving with crate, potty, and leash training. She has loved playing with other dogs, and has done well meeting all sorts of people. She has had a great start to basic puppy obedience training and safe puppy socialization. Bailee is already spayed. Her next vaccines are scheduled for July 8 This appointment is covered by the rescue. He is able to be adopted prior to the appointment as long as adopters are willing to get him to the appointment. "
    ),

    Pet(2, "Solomon", "Dachshund",
        "https://dbw3zep4prcju.cloudfront.net/animal/922fd600-c704-4a47-baae-d0ef5bdd7deb/image/5ddba02e-27f9-4819-93ad-5c0317fdebab.jpg",
        "Male", "Small",
        "Cat-friendly, Affectionate, Follower, Sweet, Couch Potato",
        "Solomon has asked us to place him in a home where he can get all the attention. He is now 14 months old and is a follower. He will follow you outside. He will follow you inside. He will follow you upstairs. He will follow you downstairs. He will follow you to the bathroom and kitchen and bedroom. :) He likes to be your shadow.\n" +
                "No cats or other dogs. No children under 8 years old please.\n" +
                "Solomon enjoys walks but has no desire to meet all of the other dogs that are walking. He's a home-body and will give you lots of loves! He is a snuggler!\n" +
                "Solomon is scheduled to be neutered later in June."
    ),
    Pet(3, "Maple", "Plott Hound",
        "https://dbw3zep4prcju.cloudfront.net/animal/0d9266af-b2db-4f44-9fe0-2bfce19411b3/image/cfab1d31-a825-4454-b4a0-cf87e4ed294d.jpg",
        "Female", "Small",
        "Cat-friendly, Playful, Curious, Independent, Athletic",
        "Maple is about 3 months old (born mid-March).\n" +
                "From her foster: She loves everything and everyone! She is very independent and is not afraid of anything. She has gotten along very well with my dog and they love to wrestle. Maple loves to play fetch and tug. She is definitely in the teething stage and can be redirected to something appropriate to chew on.\n" +
                "She does very well in the crate. She sleeps through the night and does not go potty in the crate. Potty training is going very well, but she sometimes has small accidents when excited. Maple is great at cuddling! I call her a little space heater. :) She is perfect for those who want a play mate and a dog that will cozy up to them after a long day of work."
    ),

    Pet(4, "Dale", "Hound",
        "https://dbw3zep4prcju.cloudfront.net/animal/22e7331c-c7b9-42d2-8365-46cfe4214ad6/image/d5bde6a1-fd4c-4936-8e93-c92a4e7af37f.jpg",
        "Male", "Medium",
        "Cat-friendly, Affectionate, Smart, Brave, Quiet, Couch Potato",
        "Meet Dale! Dale sat in a rural shelter for a while until a foster stepped up to save him. We have learned so much about him in foster care, and we can't wait for him to find his perfect home! Dale is a hound who is around 2 years old and full grown at about 35-40 pounds. Here are some notes from his foster family!\n" +
                "\n" +
                "\"Dale is the sweetest boy! He is very much a hound in some ways, but also has some characteristics that are surprising for a hound. For starters, he's pretty quiet! He doesn't have the hound bark/howl. Although we think he's around 2, he was likely never given a true puppyhood experience, and still has some puppy behaviors. These include wanting to chew things that are not toys, jumping up on you when excited, and still building confidence on the leash. All of these things are super easy to train and manage, and he is the type of pup who wants to learn! Dale is very dog friendly and great with cats as well. He would thrive with a family/person who will continue to build his basic obedience training, help him build confidence, and of course provide him with all the love and kindness he never got as a puppy.\""
    ),

    Pet(5, "Earl", "Mixed Breed",
        "https://dbw3zep4prcju.cloudfront.net/animal/5d736401-47d1-4579-8494-a67ba10a693c/image/e23e8f23-b76b-4482-b2ce-ef70362f5009.jpg",
        "Male", "Small",
        "Cat-friendly, Loyal, Gentle, Playful, Quiet, Athletic, Curious, Brave, Couch Potato",
        "Earl and his sister found themselves in a rural shelter just outside of Charlotte, NC. They were under one year old, and very timid and afraid. They sadly spent months there until fosters opened their homes. Earl has now been in a foster home for about a month, and he is doing amazing! We are unsure of his breed, but he is about 47 pounds and about one year old now. Here are some notes from his foster family: \n" +
                "\n" +
                "\"Earl loves other dogs and finds comfort in them. We would ideally like for him to have another dog in his forever home. He is still nervous around new people, but once he warms up to them, he is a big love bud and wants to cuddle. He has been doing wonderful with crate and potty training, and he has been amazing with all the dogs he has met. He is currently being fostered with a female dog, and they are best friends. Earl loves to play and is good at adjusting to other dogs' playstyles. He has been great around kids and cats when he's been exposed. Earl would love a home where he will be shown all the love, and bonus points if he gets to have a fur sibling!\""
    ),

    Pet(6, "Slick", "Labrador Retriever",
        "https://dbw3zep4prcju.cloudfront.net/animal/7e35e861-33a2-467d-abab-21e1e558b3c0/image/ef8895de-2c4e-4124-949c-beb5169c5914.jpeg",
        "Male", "Medium",
        "Playful, Smart, Active, High-energy",
        "This precious baby was rescued by a Good Samaritan who found out her neighbor was selling these puppies at 3 weeks old! She was able to get two and the momma, thank goodness! Mom is pictured below. She is less than knee high and weighs about 30 pounds. I don’t believe these babies will be big dogs but medium sized! Slick is currently 8 weeks old and PRECIOUS!"
    ),

    Pet(7, "Jasper", "Jack Russell Terrier",
        "https://dbw3zep4prcju.cloudfront.net/animal/4e24be5c-5309-42ff-af31-36ce6fd16de6/image/0829cfbc-d927-4e8c-a83e-398882cc4e96.jpeg",
        "Male", "Small",
        "Dog-friendly, Affectionate, Loyal, Playful, Athletic",
        "Jack Russell terrier mix\n" +
                "26 pounds\n" +
                "6 year old\n" +
                "\n" +
                "Jasper was abandoned by his owners on a rural road. They were so scared and unsure but were able to be caught by the person who dumped them son. Jasper is a total sweet boy and has zero aggression. He is crate trained and walks well on a leash. If someone is interested in a calm, very well mannered boy, he’s your boy! He does great with other dogs and loves kids. We don’t know how he is with cats. We were told he is house trained and was a house dog until he was dumped. Adopt this precious baby today!"
    ),

    Pet( 8, "Belle", "Australian Cattle Dog",
        "https://res.cloudinary.com/lancaster-puppies-laravel/image/upload/v1671032732/breeds/pmdbkvt5bkhesio3nbeb.jpg",
        "Female", "Small",
        "Dog-friendly, Affectionate, Loyal, Gentle, Smart, Dignified",
        "This little cutie is a 9 week old lab mix.\n" +
                "Mom is only 45 pounds and her picture is included below. They were all dumped at a good samaritan’s house, so we don’t know what dad was. The whole group looks like possibly fiest/cattle dog/lab mixes. Sweet, happy, and goofy pups full of kisses is the only way to describe them! Mom’s personality is very sweet, gentle, and calm! Their adoption donation covers their spay/neuter, 3 sets of puppy distemper vaccines, rabies vaccine, dewormings, and a microchip!"
    ),

    Pet(
        9, "Alvin", "Labrador Retriever",
        "https://dbw3zep4prcju.cloudfront.net/animal/c4b66dfb-54e8-4be9-804c-1f3962a2c2b0/image/314389cf-3b5e-491c-987b-62e4b1748dda.jpeg",
        "Male", "Medium",
        "Dog-friendly, Affectionate, Playful, Smart, Brave, Curious, Athletic",
        "These three precious boys were dumped at a business. They are the friendliest and happiest little guys you will meet! They are lab mixes and we aren’t sure with what as we don’t know what mom or dad was. We are guessing they will\n" +
                "be medium sized dogs"
    ),

    Pet(10, "Laney", "German Shepherd",
        "https://dbw3zep4prcju.cloudfront.net/animal/4738e961-5e2a-441a-9003-5aaf13ac5cb5/image/3c418608-8655-417d-a98b-c7760ac166e8.jpeg",
        "Female", "Medium",
        "Dog-friendly, Affectionate, Playful, Smart, Brave, Curious",
        "Laney is a sweetheart who is full of love and kisses! Laney is currently a small shepherd mix weighing 40 pounds. She is about 3-4 years old. She is an adult dog with a playful side. She enjoys spending her days snuggling up with her human friends and exploring the outdoors. She loves going on adventures or just spending the day lounging on the couch. Laney is GREAT with other dogs, kids, and doesn’t pay too much attention to cats. She is crate trained and working on house training. Laney is heartworm positive but her treatment is included in her adoption donation. The treatment is the slow kill method. She is on normal heartworm prevention twice a month and there is no exercise restrictions!"
    ),

    Pet(
        11, "Lark", "Chihuahua","https://dbw3zep4prcju.cloudfront.net/animal/5101bbf8-8f26-4f4c-ba3e-425a283f648f/image/78bae310-2b18-4041-9ac9-ea94b981422c.jpg?versionId=1_VagU4ldPAfzAcGi9whXBVR.rn5OhZJ&bust=1716582429&width=1080",
        "Female","Small","Affectionate, Protective, Funny",
        "Are you looking for a little spice in your life? Then Lark is ready to bring the hotness." +
                " She hails from St. Croix where she was a typical, stray, street dog and found her way here after being hit by a car with broken back legs." +
                " She has since made a full recovery. We are so proud of the strength and confidence she has regained. Lark has a little bit of a courting phase." +
                " But oh the joy when she latches on! Her funny, sparky personality comes alive." +
                " She doesn't have to court the ladies or pups as long as the gents, but she will come around." +
                " She deserves a furever home where she has all the toys, can steal all the socks and do all her little happy hop dance despite the spicy flavor she likes to bring to the table!"
    ),

    Pet(
        12, "Ruby", "Chihuahua", "https://dbw3zep4prcju.cloudfront.net/animal/1870c3ee-e283-4ffb-8246-8eee42dbb730/image/6b9f6032-9da9-4d03-a3a5-62c1ea09bd5f.jpeg?versionId=9Me6I.jjOhpBruoJT7YBl8smmKcDTZGv&bust=1719694254&width=1080",
        "Female", "Small", "Affectionate, Couch, Friendly, Independent, Gentle, Loyal, Loves Belly Rubs Sunbathing And Treats, Playful",
        "Meet Ruby, a charming 5-year-old female Chihuahua mix weighing in at a perfect 16 lbs. Ruby is the epitome of a loving companion who adores belly rubs and snuggling on your lap. She’s a sunbathing enthusiast who enjoys lounging in warm spots and treats are her absolute favorite! Ruby is house and crate trained, making her an easy fit into any home. She prefers the quieter things in life and gets along well with other dogs, though she appreciates a little time to warm up to new friends. Ruby loves being around people and relishes her moments as the center of attention, yet she also values her personal space. This sweet girl would make a wonderful couch companion, bringing joy and comfort to her forever home. If you're looking for a loyal friend who enjoys both quality time and serene moments, Ruby is the perfect match for you! She is spayed and current on vaccines and microchipped. Contact us today at adopt@carolinapaws.com to meet Ruby and make her part of your family."
    ),

    Pet(
        13, "Eilish","Pit Bull Terrier", "https://dbw3zep4prcju.cloudfront.net/animal/c32941f0-5599-4e12-bc7c-97caaba6a235/image/81f2585c-75ff-4895-bbcd-c72f45f3bfe5.jpeg?versionId=DPuH1Su8Uom8hxXUxivkaJxn4N80O_9v&bust=1722291321&width=1080",
        "Female","Small","Affectionate, Friendly, Playful, Smart, Curious, Funny",
        "Meet Eilish! ?\n" +
                "\n" +
                "Eilish is a sweet, sassy, and spunky 10-week-old mixed breed puppy looking for her forever home. With her adorable personality and love for play and snuggles, she’s sure to win your heart! Eilish adores her human companions and gets along wonderfully with both big and small people. She’s also great with other dogs and curious but unbothered by cats.\n" +
                "\n" +
                "Eilish is doing fantastic with her potty training and sleeps soundly through the night. She has the most beautiful eyes that she loves to use to her advantage, charming her way into getting whatever she wants!\n" +
                "\n" +
                "If you’re looking for a loving, playful, and obedient pup to join your family, Eilish could be the perfect match."
    ),

    Pet(
        14, "Charlie","Beagle","https://dbw3zep4prcju.cloudfront.net/animal/0400da21-30e1-40a6-b6fa-74907e4cef3d/image/502bdd43-c30c-40ce-ad75-dc9d2dbe6098.jpeg?versionId=HGP.tAKtkJs45VahlFp0cBsqoxWAP5tt&bust=1720961850&width=1080",
        "Male","Small","Affectionate, Friendly, Playful, Smart, Curious, Funny",
        "Weight: 24 pounds\n" +
                "House trained: no\n" +
                "Crate trained: yes\n" +
                "Fence required: No, could be leash walk only\n" +
                "Apartment/condo appropriate: yes\n" +
                "Bark meter 1-5: 2\n" +
                "Good in the car: yes\n" +
                "Good on a leash: yes\n" +
                "Good with other dogs: yes\n" +
                "Good with cats: We don’t know\n" +
                "Good with kids: yes\n" +
                "Good with Women/Men/Both: both\n" +
                "Medications: heartgard ( hw treatment)\n" +
                "Temperament: friendly, very sweet, semi playful\n" +
                "Special needs: none\n" +
                "Story: He was dumped with Lena in a very remote area. They wandered up to a home there that had other dogs.Charlie is a very light heartworm positive. He is currently on the slow kill treatment. no restrictions with exercise or anything like that. The heartworm prevention pills are included in adoption fee."
    ),

    Pet(
        15, "Fred","Labrador Retriever","https://dbw3zep4prcju.cloudfront.net/animal/e02230c0-5047-4304-9358-efc527d7bf8d/image/2a87a95f-ceef-4099-b7f2-f390d267c25e.jpg?versionId=Cl3bWc.7XmtGKq_as5MQ5LpwDFP9fp8w&bust=1721502796&width=1080",
        "Male","Medium","Dog-friendly, Cute",
        "Fred is 1.5 years old, 50 lbs.\n" +
                "\n" +
                "He received a spinal injury early in his life. Neurology dept at CARE evaluated him and said the injury was too old to fix.\n" +
                "\n" +
                "He is adapting and can go on short walks, can negotiate a stair, cannot run but can lead a fairly normal life\n" +
                "\n" +
                "He adores other dogs and follows their leads. He is special needs but just needs dogs that won't bowl him over."
    ),

    Pet(
        16, "Mr. Big", "Domestic Short Hair", "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/68744484/1/?bust=1722211444&width=1080",
        "Male","Medium","Dog-friendly, Playful, Affectionate, Curious, Independent, Agile",
        "Meet Mr. Big!\n" +
                "\n" +
                "With a big personality and an impressive physique, Mr. Big is a chatty and charming feline looking for his forever home. At 2-3 years old, he's in perfect shape, fully vaccinated, and neutered. Dogs don't bother him, and he gets along well with most kitties (although he may enjoy some playful teasing!).\n" +
                "\n" +
                "Mr. Big loves to explore the great outdoors, so an indoor/outdoor home with a safe environment is ideal for him. While he adores human company, he prefers older children who understand his boundaries. If you're ready to welcome this adventurous and lovable companion into your life, click the \"Adopt\" button for more info or to arrange a meeting!\n"
    ),

    Pet(
        17, "Herbie","Americal Short Hair","https://dbw3zep4prcju.cloudfront.net/animal/03a65930-1e0a-490e-9975-dd78dc849075/image/174422a7-2f8a-4ee1-83fc-74dfb59b06d9.jpg?versionId=4htwt43tVAE3SC0ObtjYJaOI0n9V9Uay&bust=1721784702&width=1080",
        "Male","Medium","Affectionate, Quiet, Loyal, Gentle, Friendly",
        "Coat length: \n" +
                "Short\n" +
                "House-trained: \n" +
                "Yes\n" +
                "Health: \n" +
                "Vaccinations up to date, spayed / neutered.\n" +
                "Good in a home with: \n" +
                "Other cats, dogs, children."
    ),

    Pet(
        18, "Little Man","American Short Hair","https://dbw3zep4prcju.cloudfront.net/animal/34ffe533-9f43-450c-96d4-c591d132754c/image/dd54598d-eca3-42c1-9c78-198e2ff237be.jpg?versionId=dWvlmMiALqd7Lo.tBiQzslW61y5pgu7k&bust=1721819768&width=1080",
        "Male","Small","Affectionate, Athletic, Brave, Couch, Curious, Dignified, Friendly, Funny, Gentle, Independent, Loves, Playful, Protective, Quiet, Loyal, Smart",
        "I was found at four weeks old I was So little and sick but a lovely lady kept checking on me and giving me meds till she found me a safe place. My colony was not safe and my mom left me.\n" +
                "Now I’m spoiled rotten and will be looking to find a furever family in two weeks."
    ),

    Pet(
        19, "French Fry", "Domestic Short Hair", "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/72054397/1/?bust=1722380766&width=1080",
        "Male", "Small", "Dog-friendly, Cute",
        "French Fry loves to play and take long cat naps. He especially loves to wrestle with his sister, Zesty and any other kittens around. Nobody is a stranger to him!\n" +
                "\n" +
                "If kitty has not yet been spayed or neutered they may still go home if the approved adopter agrees to sign a legal contract certifying that they will take Kitty to the spay/neuter appointment."
    ),

    Pet(
        20, "Drogon", "Russian Blue", "https://dbw3zep4prcju.cloudfront.net/animal/f8800bc7-dd6b-45c3-a5f2-4e3e4d3f4af9/image/b8cd3d66-a2c7-49e9-8e8f-5c2f90932623.jpeg?versionId=AYEzaHRCsmtaGhz0jJ4Sv6i2UHvN0Qm1&bust=1721620352&width=1080",
        "Male", "Medium", "Dog-friendly, Affectionate, Curious, Friendly, Independent, Playful",
        "Drogon and his brothers Balerion and Morghul are SO sweet! They’re incredibly affectionate and love to snuggle. While they’re enjoying the best kittenhood has to offer in their foster home, they would each really love a home of their own."
    ),

    Pet(
        21, "September", "Domestic Short Hair",
        "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/72072169/1/?bust=1719985470&width=300",
        "Male", "Small",
        "Dog-friendly, Cute",
        "Meet September, the adorable Domestic Short Hair cat who's sure to steal your heart! " +
                "This small male feline is not only cute but also incredibly friendly, especially with dogs. " +
                "His charming personality and playful demeanor make him the perfect addition to any family."
    ),

    Pet(22, "Nene", "Domestic Short Hair",
        "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/71845070/1/?bust=1719984701&width=300",
        "Female", "Small",
        "Dog-friendly, Playful, Affectionate, Curious, Independent, Agile",
        "Nene was one of 5 kittens dumped on a remote road in York County and left to fend for themselves. While 3 kittens readily entered the humane trap, Nene and Drew proved to be both stubborn and resilient! It took 3 days for their rescuer to catch them and bring to safety. The rescue itself had to get creative as the kittens found themseleves stuck in a storm drain. Ultimately, tying a blanket to the grate and lowering it to the ground provided just the right thing for them to climb out. Once out, neither could resist the smell of fresh sardines and they entered the trap together.\n" +
                "\n" +
                "While Nene is still on the shy side, she does allow her human to pick her up for short period. She enjoys being loved on and pet and shows that she is capable of being an amazing companion for a quieter home - no small children please! She would thrive being adopted with a foster friend, or into a home with a playmate. Her preferred naptime spot is by a window where she can watch the birds."
    ),

    Pet(23, "Asher", "American Short Hair",
        "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/72124640/1/?bust=1720323573&width=300",
        "Male", "Medium",
        "Dog-friendly, Affectionate, Curious, Friendly, Independent, Playful",
        "The adorable boys ready for his forever home. He is house trained and has all the vaccinations up-to-date."
    ),

    Pet(24, "Peugeot", "Domestic Medium Hair",
        "https://res.cloudinary.com/petrescue/image/upload/b_auto:predominant,f_auto,c_pad,h_638,w_638/v1631689440/dy6haq2rogawcpn9lnfn.jpg",
        "Male", "Small",
        "Cat-friendly, Dog-friendly, Playful, Curious, Affectionate, Agile",
        "Meet Peugeot, a wonderful feline companion with a unique personality. Peugeot is known for being sociable, gentle, and intelligent, making every day an adventure. With a sleek coat and bright eyes, Peugeot is as beautiful as he is charming. He enjoys climbing to high places, engaging in interactive play, and basking in warm spots, and he has a knack for making everyone around him smile. Whether you're looking for a cuddly lap cat or a curious explorer, Peugeot is ready to bring joy and love into your home. Adopt Peugeot today and discover the endless happiness he can offer."
    ),

    Pet(25, "May", "Domestic Short Hair",
        "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/71573001/1/?bust=1720323589&width=300",
        "Female", "Small",
        "Cat-friendly, Dog-friendly, Playful, Curious, Independent, Affectionate, Agile",
        "Meet May, a wonderful feline companion with a unique personality. May is known for being playful, affectionate, and curious, making every day an adventure. With a sleek coat and bright eyes, May is as beautiful as she is charming. She enjoys playing with toys, relaxing in sunny spots, and exploring her surroundings, and she has a knack for making everyone around her smile. Whether you're looking for a cuddly lap cat or an energetic playmate, May is ready to bring joy and love into your home. Adopt May today and discover the endless happiness she can offer."
    ),

    Pet(26, "Lexi", "Abyssinian",
        "https://dbw3zep4prcju.cloudfront.net/animal/cd8dfafd-8299-4e82-8f32-f0a4ce2fd047/image/16f2b70e-9c88-48b4-9464-701a2487047d.jpg",
        "Female", "Medium",
        "Cat-friendly, Dog-friendly, Smart, Playful, Independent",
        "Lexi is a beautiful 9 year old female Abyssinian. She is not happy living in a multi-cat household. One-on-one with her people, she craves attention. She loves sitting in the window watching birds and squirrels. Wave a feather toy and you'll have her attention. She loves high places, like most Abys. This pretty girl is searching for a home where she can be cherished as your one and only feline companion."
    ),

    Pet(27, "Moose", "Bengal",
        "https://dbw3zep4prcju.cloudfront.net/animal/b713cfb7-234e-4870-9956-47a47ed2b682/image/bdbedbce-743a-49eb-a349-1c72e68c2ee6.jpg",
        "Male", "Medium",
        "Cat-friendly, Dog-friendly, Loyal, Playful, Friendly, Smart, Curious",
        "Moose's coat length is short. He is well house-trained and is friendly. All the vaccinations are up-to-date, spayed and neutered. Also, he is very good with other cats and dogs."
    ),

    Pet(28, "Chester", "British Short Hair",
        "https://dbw3zep4prcju.cloudfront.net/animal/bcc34188-453a-4d30-b813-b7d99bd2ca36/image/9cb60834-b56a-4b5c-962f-f31dd9b72291.jpg",
        "Male", "Medium",
        "Cat-friendly, Dog-friendly, Affectionate, Friendly, Gentle, Quiet",
        "Chester has, until now, had a hard life. He lived in a colony outside for years. The lady feeding the colony requested euthanasia when he tested positive for both Feline AIDS and Feline Leukemia. Despite the diagnosis, Chester is healthy and vigorous. He is a healthy weight, has clear eyes, eats well, uses his litter box, and plays with his toys. He is also very loving and craves attention, which of course he did not get all those years of living outside. We know that Chester still has a lot of love to give to the right family. Please help us help him."
    ),

    Pet(29, "Tinky", "Dilute Calico",
        "https://dbw3zep4prcju.cloudfront.net/animal/45cd62de-bcbd-4c7b-841e-71622d68bd4b/image/a588e539-0bc0-4eae-9e65-200e76e8c5c2.jpg",
        "Male", "Medium",
        "Cat-friendly, Dog-friendly, Friendly, Gentle, Affectionate, Curious, Smart",
        "Remember Winky and Tinky, our special needs kittens of 2020?\n" +
                "In 2020, at 4 weeks old, they had upper respiratory infections and badly infected eyes! Unfortunately, Winky (female) lost sight in one eye and Tinky (male) has limited vision in one eye. As you can see in Tinky’s photos, there is one of him as a kitten with his infected eye. Winky’s eye still leaks but vet said to expect that with scar tissue. Her eye is deflated but not painful. It isn’t beautiful to look at but isn’t causing issues now.\n" +
                "They were adopted as kittens in December 2020 by a wonderful family. Through no fault of anybody (humans or Winky and Tinky) they need a new home, preferably semi-quiet, not an extremely hectic household. They are healthy and have been well cared for.\n" +
                "They are loving cats that would be wonderful family members. Still a bonded pair, they need to be adopted together. The adoption fee is \$125.00 for the pair.\n" +
                "They were combo-tested negative and they are up to date on vaccinations. They have been spayed and neutered."
    ),

    Pet(30, "Cookie", "Dilute Calico",
        "https://dbw3zep4prcju.cloudfront.net/animal/ab66eb58-776b-444e-98db-da7700c3d6d0/image/2709203a-e8e0-458a-8caa-ec4f1807cf8c.jpg",
        "Female", "Medium",
        "Cat-friendly, Dog-friendly, Playful",
        "Cookie was found living by homeowners living in their grill. Unfortunately, they are allergic to cats so reached out for help. She’s an energetic girl that loves to play and purr while doing it. Cookie didn’t have any issues adjusting to her foster home or any of the other cats. If anything, she was happy because now she had feline friends to be with. Her medium fur is very plush and she’s so adorable!"
    ),

    )

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailablePetsScreen(navController: NavController, pets: List<Pet>){

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Paws") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Go Back"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            //Main Container
            Box(modifier = Modifier.fillMaxSize()) {
                // Background image
                Image(
                    painter = painterResource(id = R.drawable.bg),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // Column layout to display the filter button and list of pets
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp)
                ) {
                    // Filter button
                    Button(
                        onClick = { navController.navigate("filter") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(Color(0xFFFFA726), Color(0xFFFF7043))
                                )
                            )
                            .shadow(8.dp, RoundedCornerShape(12.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Text(text = "Filter", fontSize = 18.sp, color = Color.White)
                    }


                    Spacer(modifier = Modifier.height(16.dp))

                    if (pets.isEmpty()) {
                        // Check if there are any available pets
                        // Display message if no pets are available
                        Text(
                            text = "No such pet is available for now! ",
                            color = Color.White,
                            fontSize = 20.sp,
                            modifier = Modifier.align(
                                Alignment.CenterHorizontally
                            )
                        )
                    } else {
                        // LazyColumn to display the list of available pets
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
                                        PetCard(navController = navController, pet = pet)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

@Composable
fun PetCard(navController: NavController, pet: Pet) {
    // Card to display pet details
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate("pet_detail/${pet.id}") }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                // Display the pet image
                Image(
                    painter = rememberAsyncImagePainter(model = pet.imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize() // Adjust the height as needed
                )
            }
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Display the pet's name and breed
                    Text(
                        text = pet.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(0xFF000000)
                    )
                    Text(text = pet.breed, fontSize = 14.sp, color = Color(0xFF5d4037))
                }
            }
        }
    }