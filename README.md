
![Logo](https://raw.githubusercontent.com/Mushmat/Wiggles/master/Picture1.png?token=GHSAT0AAAAAACTES5MICMSBZ5Z7DA7BPTSEZUWUKDQ)


# Wiggles 

(**Prototype Version**)

Wiggles is a comprehensive mobile application designed to faciliate the adoption process for cats and dogs. The app connected potential pet owners with pets needing a home, providing detailed profiles and personalized recommendations to ensure the perfect match. With a focus on ease of use, Wiggles aims to streamline the adoption process and support pet owners with valuable resources. 


## 🎯 Objectives

- **Promote Pet Adoption**: Increase the adoption rates by providing a user-friendly platform where users can browse and adopt pets. 
- **Personalized Recommendations**: Match users with pets that fit their lifestyle and preferences using a quiz-based recommendation system. 
- **Support Pet Owners**: Offer resources such as pet care tips, nearby vet locations ,and community support to ensure a smooth transition for new pet owners.
- **Comprehensive Pet Profile**: Provide detailed information about each pet to help users make informed decisions. 
## 🔍 Features
- Exclusive Pet Profiles for every pet
- Pet Adoption through a Hassle-Free Process
- Pet Quiz for Personalized Recommendations
- Data Persistence (Room Database)
- User Authentication (Firebase)
- Adoption Request Tracking
- Pet Filtering and Bookmarking
- Pet Care Tips
- Nearby Pets Locator
- Connects to different Pet Owners
- Modern UI & Easy-to-Use
- Shelter Information
- Informational Screens such as About Us, FAQs, etc. 


## 🛠️ Implementation Details

- **User Authentication**: Secure user login and signup using Firebase Authentication.

- **Data Persistence**: Local storage using Room Database to keep track of user profiles, bookmarked pets, and adoption applications.

- **Pet Profiles**: Detailed pet profiles are displayed using data fetched from a predefined dataset.

- **Personalized Quiz**: A quiz-based system that collects user preferences and suggests pets accordingly.

- **Adoption Application**: Users can submit adoption applications and track their progress within the app.

- **Modern UI Design**: A visually appealing interface designed using Jetpack Compose, with gradient buttons and customized themes.

- **Navigation**: Seamless navigation throughout the app using Jetpack Navigation Component.


## 📥 Installation

To run the Wiggles App, follow these steps:

### Prerequisites

Before you can run the Wiggles App, ensure you have the following installed on your system:
- [Android Studio Koala (latest version as of July 2024)](https://developer.android.com/studio)

- [Git (for cloning the repository)](https://git-scm.com/downloads)

- An Android device or emulator for testing

### Steps to Install and run
 1. **Clone the Repository**
 - Go to the Github repository and click on the **'Code'** button.

 - Copy the HTTPS web URL:

```bash
https://github.com/Mushmat/Wiggles.git
```
2. **Open the Project in Android Studio**
- Open Android Studio (latest version)

- Click on **'File'** from the top bar.
- Select **'New'**, then click **'Open from Version Control'**.
- Paste the copied URL into the URL section and enter a directory location for the app  files to save in.
- Click on the **'Clone'** button at the bottom left.
3. **Wait for Gradle Build**
- The files will be cloned and the project will open shortly once the Gradle build is finished.
4. **Run the App**
- Once the Gradle build is complete, you can run the app using the play button present on the top bar. 

- Select an emulator or connect an Android device to run the app.

### Additional Setup (Optional)
If you encounter any issues or need further customization, refer to the following resources:

- [Android Studio Documentation](https://developer.android.com/studio/intro)
- [Firebase Documentation](https://firebase.google.com/docs)


## 📚 Project Documentation

[Documentation](https://linktodocumentation)


## 🛠️ Tech Stack

The Wiggles App is build using the following technologies:

**Frontend:** Kotlin, Jetpack Compose, Android Studio

**Backend:** Firebase Authentication, Firebase Firestore

**Libraries and Tools:** Coil, Room Database, Navigation Component, Gradle

**Additional Services:** Github



## 🎨 Color Reference

| Color             | Hex                                                                |
| ----------------- | ------------------------------------------------------------------ |
| Dark Blue | ![#1a1a73](https://placehold.co/15x15/1a1a73/1a1a73.png) #1A1A73 |
| Orange | ![#ff7043](https://placehold.co/15x15/ff7043/ff7043.png) #FF7043 |
| Black | ![#000000](https://placehold.co/15x15/000000/000000.png) #000000 |
| Purple | ![#8e44ad](https://placehold.co/15x15/8e44ad/8e44ad.png) #8E44AD |
| Red | ![#FF0000](https://placehold.co/15x15/ff0000/ff0000.png) #FF0000 |
| Green | ![#388e3c](https://placehold.co/15x15/388e3c/388e3c.png) #388E3C |
| Light Blue | ![#3498db](https://placehold.co/15x15/3498DB/3498DB.png) #3498DB |

## 🚧 Challenges Faced

What challenges did you face and how did you overcome them?

- **Problem**: Ensuring that the app remains responsive while processing complex AI algorithms in the background.
- Implemented asynchronous processing and optimized the algorithms to run efficiently without blocking the main UI thread.
- **Problem**: Designing the app to handle a growing user base and increasing data volume.
- Utilized cloud services for scalable storage and processing, and designed the architecture to support easy scaling by adding more resources as needed. 
- **Problem**: Ensuring user data is stored and synchronized correctly across different devices and user accounts.
- Integrated Firebase for user authentication and real-time database, ensuring secure and consistent data storage and synchronization. 
- **Problem**: Designing an engaging and intuitive user interface that caters to diverse user needs. 
- Conducted user research and usability testing to gather feedback and iteratively improve the UI design based on user preferences and pain points. 


## 🔮 Future Scope

- **Realtime data using API**: This is a prototype version. In the future versions, we will collaborate with pet shelters to fetch real-time data on available pets and streamline the adoption process. 

- **Services for pet parents**: Implementing services for parents who have adopted the pet and are looking for future care such as vaccination schedules, play areas, etc.
- **In-App Messaging**: Implement a messaging feature to allow users to communicate directly with shelters and other pet owners for adoption inquiries and advice.
- **Advanced Filtering**: Enchancing the filtering options to include more criterias such as pet age, health status, and special needs.
- **AI-Powered Recommendations**: Leveraging machine learning to improve the accuracy of pet recommendations based on user behaviour and feedback.
- **Stronger Authentication**: Adding SMS verification for enhanced security during user registration and implementing email notifications for adoption application updates and reminders. 
- **Volunteer Opportunities**: Provide a platform for users to find and sign up for volunteer opportunities at local shelters and pet adoption events.
- **Integration with Social Media**: Allow users to share their pet adoption stories and profiles directly to social media platforms to raise awareness and encourage more adoptions.
- **Donate**: This option is a coming-soon feature in the app, currently. Soon, the users would be able to donate to the shelters for the betterment of the pets. 

## ❓ FAQs

#### Q1. What is Wiggles?

Wiggles is an Android application designed to help users find and adopt pets. It offers features like pet search, bookmarking, adoption applications, pet care tips, and more.

#### Q2. What are the system requirements to run the Wiggles app?

To run the Wiggles app, you need:

- Android Studio (latest version)

- Java Development Kit (JDK) 8 or higher

- Android Emulator or a real Android device

- Internet connection for cloning the repository and fetching dependencies

#### Q3. How can I contribute to the Wiggles project?

To contribute to the Wiggles project:

- Fork the repository.

- Create a new branch for your feature or bug fix.

- Make your changes and commit them with clear messages.

- Push your changes to your forked repository.

- Create a pull request to the main repository.

#### Q4. How do I report a bug or request a feature?

You can report bugs or request features by creating an issue on the GitHub repository. Please provide detailed information about the bug or feature request.

#### Q5. Are there any costs associated with using Wiggles?

No, Wiggles is a free-to-use application.

#### Q6. Can I use Wiggles on iOS devices?

Currently, Wiggles is developed for Android devices only. Future plans may include developing an iOS version.
## 🙌 Acknowledgements

 - [Pet Finder ](https://www.petfinder.com/) : For providing inspiration and resources for pet adoption. 
 - [Android Studio Developers](https://developer.android.com/develop) : For their comprehensive development tools and resources.
 - [Firebase](https://firebase.google.com/) : For offering robust backend services including authentication, real-time databases, and more.
 - [Coil](https://coil-kt.github.io/coil/) : For providing an efficient image loading library for Android.
 - [Github](https://github.com/) : For their advanced language models that assisted in generating content and code suggestions.
 - [OpenAI](https://openai.com/) : For their advanced language models that assisted in generating content and code suggestions.
 - To all the contributors and developers who provided valuable feedback and support during the development of this project.


