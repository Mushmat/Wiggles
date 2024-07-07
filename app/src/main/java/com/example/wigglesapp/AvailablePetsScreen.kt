package com.example.wigglesapp

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController

val dummyPets = listOf(
    Pet(1, "Bailey","Pit Bull Terrier","https://dbw3zep4prcju.cloudfront.net/animal/3cc08a4b-ffdc-47a5-bb8a-3a62147ce7cb/image/3a36eb40-c7e6-470c-9206-f5bacdcebc2b.jpg?versionId=zZ8cPVw8vhdfM6z0sSQ3EKfiBnvzIJM8&bust=1719713935&width=720"),
    Pet(2, "Solomon","Dachshund","https://dbw3zep4prcju.cloudfront.net/animal/922fd600-c704-4a47-baae-d0ef5bdd7deb/image/5ddba02e-27f9-4819-93ad-5c0317fdebab.jpg?versionId=zpMokUb.EZiJrc_JdimO_kStXobV3O3w&bust=1718220692&width=720"),
    Pet(3, "Maple","Plott Hound","https://dbw3zep4prcju.cloudfront.net/animal/0d9266af-b2db-4f44-9fe0-2bfce19411b3/image/cfab1d31-a825-4454-b4a0-cf87e4ed294d.jpg?versionId=qCde0vHBgQXaybeCUS..5agtHGJ9VCcp&bust=1718220241&width=720"),
    Pet(4, "Dale","Hound","https://dbw3zep4prcju.cloudfront.net/animal/22e7331c-c7b9-42d2-8365-46cfe4214ad6/image/d5bde6a1-fd4c-4936-8e93-c92a4e7af37f.jpg?versionId=U.Ao8lyG0divcXiDn.duZYatJx69gsd2&bust=1717684331&width=720"),
    Pet(5, "Earl","Mixed Breed","https://dbw3zep4prcju.cloudfront.net/animal/5d736401-47d1-4579-8494-a67ba10a693c/image/e23e8f23-b76b-4482-b2ce-ef70362f5009.jpg?versionId=HTju8FSkvuJxIA3sMqLojqkVYMSE_Vpf&bust=1714782863&width=720"),
    Pet(6, "Slick","Labrador Retriever","https://dbw3zep4prcju.cloudfront.net/animal/7e35e861-33a2-467d-abab-21e1e558b3c0/image/ef8895de-2c4e-4124-949c-beb5169c5914.jpeg?versionId=09HyRzi88J6E8nXBQmc4jqyC4aeyFvcq&bust=1719666397&width=720"),
    Pet(7, "Jasper","Jack Russell Terrier","https://dbw3zep4prcju.cloudfront.net/animal/4e24be5c-5309-42ff-af31-36ce6fd16de6/image/0829cfbc-d927-4e8c-a83e-398882cc4e96.jpeg?versionId=7LyfnpmQKCmQpVE3mi1lmWIfd_m8x6NX&bust=1717295973&width=720"),
    Pet(8, "Belle","Australian Cattle Dog","https://dbw3zep4prcju.cloudfront.net/animal/0efe8dfb-94ea-4cd8-9870-3ce3870f3e8d/image/45c68613-925d-47a9-a3ed-18567f407a6f.jpeg?versionId=8ODrfw.56VLA7p9ggNmIgFgUPZAJZErz&bust=1712338857&width=720"),
    Pet(9, "Alvin","Labrador Retriever","https://dbw3zep4prcju.cloudfront.net/animal/c4b66dfb-54e8-4be9-804c-1f3962a2c2b0/image/314389cf-3b5e-491c-987b-62e4b1748dda.jpeg?versionId=cRZOFJRcyASvEiqclJGqNsuVBUCdHvYS&bust=1713803507&width=720"),
    Pet(10, "Laney","German Shepherd","https://dbw3zep4prcju.cloudfront.net/animal/4738e961-5e2a-441a-9003-5aaf13ac5cb5/image/3c418608-8655-417d-a98b-c7760ac166e8.jpeg?versionId=G.JTG8rOuXw0bpLjD1y8C4tEn.9.1tL.&bust=1712335555&width=720"),
    Pet(11, "Yara","Fox Hound","https://dbw3zep4prcju.cloudfront.net/animal/e6e1ba9a-ab0a-480d-9434-d2c0f8b5996f/image/0b121871-90af-4d45-91eb-263e3e2d2c60.jpg?versionId=kXB5JdfK.bFFZAknx1QTRWauoFUuS2Dp&bust=1719689752&width=720"),
    Pet(12, "Waffles","Pit Bull Terrier","https://dbw3zep4prcju.cloudfront.net/animal/3b721da5-0e5d-4373-936d-75c44d7d5935/image/47601ceb-da04-4089-8c6b-3ffaccdf87bb.jpg?versionId=hfOpzq63z9.2c.91_qxivCqo0MS8pLBK&bust=1719087565&width=720"),
    Pet(13, "Mo","Chiweenie","https://dbw3zep4prcju.cloudfront.net/animal/4ac9f7ba-34be-438e-be0a-4acd5ba20552/image/92bdb274-dff5-4db6-b4aa-70acc97b7ab8.jpg?versionId=b9jAr2cN0UVQTKjUcUIriRqp_k3HXtoR&bust=1719086608&width=720"),
    Pet(14, "Buster","Boxer","https://dbw3zep4prcju.cloudfront.net/animal/103824a4-f401-4a39-a00e-f41fc59a0475/image/85b74498-8689-4f0e-b59f-44d5981442af.jpg?versionId=o86_73E9UJIxmyRVLtYfg6754efVHyxY&bust=1718381843&width=720"),
    Pet(15, "Harper","Boxer","https://dbw3zep4prcju.cloudfront.net/animal/631e15b7-75a5-4847-8e73-a9859260cc3e/image/20b826ec-a818-48c0-b6b2-62a1ec9d0ec0.jpeg?versionId=VfPTtWzoRVk6Mts_WyFsRPT0nHQpt3Kr&bust=1719183516&width=720"),
    Pet(16, "Nikki","Pomeranian","https://dbw3zep4prcju.cloudfront.net/animal/421ce142-655e-48cb-92fb-6c33fc13b99e/image/cf13396e-667c-4253-967d-fa3ebbc5a15e.jpeg?versionId=5X3zf7mfMepBOirT60oBOimiV23ehkIs&bust=1716643901&width=720"),
    Pet(17, "Lark","Chihuahua","https://dbw3zep4prcju.cloudfront.net/animal/5101bbf8-8f26-4f4c-ba3e-425a283f648f/image/78bae310-2b18-4041-9ac9-ea94b981422c.jpg?versionId=1_VagU4ldPAfzAcGi9whXBVR.rn5OhZJ&bust=1716582429&width=720"),
    Pet(18, "Ruby","Chihuahua","https://dbw3zep4prcju.cloudfront.net/animal/f78d3c5b-4e70-49b8-99f7-0fe32f4c6583/image/cfcee275-15a7-4aec-a12c-42e5263be40d.jpg?versionId=OK7UKoj7JrJQOrznHQRZav7F9sTgeWsT&bust=1711892909&width=720"),
    Pet(19, "Polly","Terrier","https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/72185007/1/?bust=1720243354&width=720"),
    Pet(20, "Neville","Beagle","https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/72216731/1/?bust=1720246553&width=720"),

    Pet(21,"September","Domestic Short Hair","https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/72072169/1/?bust=1719985470&width=300"),
    Pet(22,"Nene","Domestic Short Hair","https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/71845070/1/?bust=1719984701&width=300"),
    Pet(23,"Asher","American Short Hair","https://dbw3zep4prcju.cloudfront.net/animal/4d562d02-c606-4bcb-aa41-62a2a18ffe4c/image/3893469b-17fa-43fb-948a-09c84b82e2d0.jpg?versionId=Id5SEKJNyBvkL2FInEXR4j42Y49GE7G3&bust=1719825873&width=300"),
    Pet(24,"Peugeot","Domestic Medium Hair","https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/72038617/1/?bust=1720128776&width=300"),
    Pet(25,"May","Domestic Short Hair","https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/71573001/1/?bust=1720323589&width=300"),
    Pet(26,"Lexi","Abyssinian","https://dbw3zep4prcju.cloudfront.net/animal/cd8dfafd-8299-4e82-8f32-f0a4ce2fd047/image/16f2b70e-9c88-48b4-9464-701a2487047d.jpg?versionId=LPrLMCS_4cVHpy6kc2Z7f8ux4CTfmfeh&bust=1712614228&width=300"),
    Pet(27,"Moose","Bengal","https://dbw3zep4prcju.cloudfront.net/animal/b713cfb7-234e-4870-9956-47a47ed2b682/image/bdbedbce-743a-49eb-a349-1c72e68c2ee6.jpg?versionId=7LgY6j.XmDTPMgZgRkFx1fXBvSiaFQHI&bust=1712600739&width=300"),
    Pet(28,"Chester","British Short Hair","https://dbw3zep4prcju.cloudfront.net/animal/bcc34188-453a-4d30-b813-b7d99bd2ca36/image/9cb60834-b56a-4b5c-962f-f31dd9b72291.jpg?versionId=YGMAdKfdGELGXUaMIj_uZwmEbmhkA7N3&bust=1719675861&width=300"),
    Pet(29,"Tinky","Dilute Calico","https://dbw3zep4prcju.cloudfront.net/animal/45cd62de-bcbd-4c7b-841e-71622d68bd4b/image/a588e539-0bc0-4eae-9e65-200e76e8c5c2.jpg?versionId=TsACFmj51hGkR4BuzJkc6Ns4stIiHH0j&bust=1712650429&width=300"),
    Pet(30,"Cookie","Dilute Calico","https://dbw3zep4prcju.cloudfront.net/animal/ab66eb58-776b-444e-98db-da7700c3d6d0/image/2709203a-e8e0-458a-8caa-ec4f1807cf8c.jpg?versionId=qM9OGOeicegrZDTTIUFbQew8CTuw0_qZ&bust=1718826402&width=300"),

    )

@SuppressLint("RememberReturnType")
@Composable
fun AvailablePetsScreen(navController: NavController){
    val pets = remember { dummyPets }
}