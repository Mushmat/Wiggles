
![Logo](https://i.ibb.co/9GwTpSD/Picture1.png)

# Wiggles Code Documentation

(**Prototype Version 0.9.0-alpha**)

## 👋 Introduction

- **Project Name**: Wiggles
- **Version**: 0.9.0-alpha
- **Author**: Chirayu Choudhary
- **Date**: August 9, 2024
- **Purpose**: Wiggles is an Android application designed to facilitate pet adoption by allowing users to browse available pets, submit adoption requests, and track the status of their applications.

## 🌐 Overview

### Architecture:
Wiggles follows the MVVM (Model-View-ViewModel) architecture pattern to separate the UI logic from business logic, ensuring maintainability and scalability.

### Modules:

- **UI:** Contains all the UI-related components like Activities, Fragments, and Composables.
- **Viewmodels:** Houses all the ViewModel classes responsible for managing UI-related data.
- **Data:** Manages data sources including Room Database, SharedPreferences, and Firestore integration.
- **Models:** Defines the data models used throughout the application.
- **Components:** Custom UI components like buttons and dialogs.

### Dependencies:

- **AndroidX:** Core libraries for Android development.
- **Jetpack Compose:** For building UI components.
- **Room:** For local database management.
- **Firebase Firestore:** For cloud storage and data syncing.
- **Retrofit:** For network requests.
- **Hilt:** For dependency injection.
- **Coil** For image loading.

## 🛠️📂 Code Structure

### Package Structure:

- **ui:** Contains screens like Login, SignUp, AdoptionTracker and components for UI rendering.
- **viewmodels:** Manages the UI-related data for screens using ViewModels like AuthViewModel and SharedViewModel.
- **data:** Includes database classes, DAOs, and Firestore setup.
- **models:** Data models like UserProfile, AdoptionApplicationEntity, and Pet.
- **components:**  Custom UI components such as GradientButton.

### Important Classes/Files:

- **MainActivity:** The entry point of the app, setting up navigation.
- **SharedViewModel:** Central ViewModel handling data synchronization between UI and Firestore/Room.
- **AuthViewModel:** Manages authentication logic including login, signup, and logout.
- **AppDatabase:** Defines the Room database configuration.
- **Firebase Functions:**  JavaScript functions for sending emails on adoption request status updates.

### Resource Files:

- **layout/**: XML layouts (not applicable if fully Compose-based).
- **drawable/**:  Images and vectors used in the UI.
- **values/**: Strings, colors, and other resource values.

## 📝📖 Detailed Documentation

### Activity/Fragment Documentation:

- **MainActivity:** Sets up navigation and serves as the container for all screens.
- **LoginScreen:** Handles user authentication via email and password.
- **SignUpScreen:** Allows users to create an account by entering personal details.
- **AboutUsScreen:** Allows users to read about the application and its purpose.
- **AdoptionApplicationScreen**: Sets up the adoption screen for the user to submit an adoption request. 
- **AdoptionSuccessScreen**: Adoption request acknowledgment screen for the user.
- **ApplicationDetailScreen**: Handles all the details the user submitted in the request.
- **ApplicationTrackerScreen**: Allows users to track their adoption requests.
- **AuthScreen:** Handles login and signup.
- **AvailablePetsScreen:** Allows users to select from available pets up for adoption.
- **BookmarkedPetsScreen:** Displays the bookmarked pets for the user, if any. 
- **CatTipsScreen**: Displays swipeable cat tips for the user's information.
- **ContactUsScreen**: Allows users to reach out to the developer(s).
- **DogTipsScreen**: Displays swipeable dog tips for the user's information.
- **FAQsScreen:** Allows users to read through the frequently asked questions.
- **FilterScreen:** Handles the filtering facility for the pets up for adoption.
- **GeneralTipsScreen:** Displays swipeable general tips for the user's information.
- **HomeScreen:** Allows users to choose from various functions available to use at the home page.
- **NavigationDrawer:** Holds the drawer items and the navigation to each of them. 
- **PetCareScreen:** Displays the options for pet care for the user's information and help.
- **PetCareTipsOptionsScreen**: Holds the navigation buttons to the three types of tips.
- **PetDetailScreen:** Handles the data of each pet and displays it to the user when needed. Also provides the option to bookmark or adopt them.
- **PetQuizScreen:** An interactive window for the user to express their choices and get a pet suggested.
- **SamePetParent:** Allows the users to contact previous adopters.
- **ShelterInfoScreen:** Handles the information of the shelters.
- **SuggestedPetsScreen:** Displays the suggested pet to the user based on their answers in the quiz.
- **TestimonialsScreen:** Allows users to read through the testimonials of the previous adopters.
- **UserProfileScreen:** Handles the user data and allows them to modify it.

### ViewModel Documentation:

- **AuthViewModel**: Manages authentication states, handles login, signup, and user details fetching.
- **SharedViewModel**: Centralized ViewModel for managing adoption requests, bookmarks, and Firestore synchronization.

### Service/Receiver Documentation::

- **Firebase Functions**: Used for sending emails when an adoption request is accepted or rejected.

### Database/storage:

- **Room Database**:  Stores user profiles and bookmarked pets locally.
- **Firestore**: Stores adoption applications and synchronizes data across devices.

### Networking:

- **Retrofit**: Used for making network requests (if applicable).

## 📡📘 API Documentation

### API Endpoints:

- **Firestore Collections:** *adoptionRequests/*: Stores the details of each adoption application.

### Authentication:

- **Firebase Authentication:** Used for managing user accounts via email and password.

## 🚨🔧 Error Handling

### Common Errors:

- **Firebase Authentication Errors:** Handles sign-in failures due to incorrect credentials.
- **Firestore Errors:** Handles data synchronization issues.


### Logging:

- **Logcat:** Used for debugging in Android Studio.
- **Firebase Crashlytics:** (Optional) for crash reporting in production.

## ✅🧪 Testing

### Unit Tests:

- **Junit & Mockito**: Used for unit testing ViewModel logic.
### UI Tests:

- **Espresso**: Used for UI testing.
### Test Coverage:

- **Jacoco**: For generating test coverage reports.

## 🚀📦 Build and Deployment

### Build Process:

- **Gradle**: Used for building the project, managing dependencies, and signing the APK.
### Continuous Integration:

- **GitHub Actions:**: Set up for automated builds and tests (optional).
### Deployment:

- **Google Play Store:**: Steps for deploying the app to the Play Store.

## 🤝✨ Contribution Guidelines

### How to Contribute

- Fork the repository and create a new branch for your feature or bug fix.
- Ensure your code follows the project's coding standards and is well-documented.
- Submit a pull request with a clear description of your change.

### Pull Request Process

- All pull requests must be reviewed by at least one maintainer.
- Make sure your pull request passes all automated tests.
- Include any relevant screenshots or explanations for UI changes.

## ❓📚 FAQ

### Common Questions

- **Q: Is Wiggles free?**
  - A: Completely free to use.

- **Q: What should I do if the app crashes?**
  - A: Please report the issue via the feedback section or email us with the steps to reproduce the issue.

## 🔗📜 References

### External Documentation

- [Android Developer Documentation](https://developer.android.com/docs)
- [Kotlin Language Documentation](https://kotlinlang.org/docs/reference/)

### Further Reading

- **Book:** _Android Programming: The Big Nerd Ranch Guide_
- **Article:** [Understanding Android Jetpack](https://developer.android.com/jetpack/)

## 📑🔍 Appendix

### Glossary

- **MVVM:** Model-View-ViewModel, an architectural pattern used in software development.
- **CI/CD:** Continuous Integration/Continuous Deployment, a method to automate code testing and deployment.

### Changelog

- **v0.9.0-alpha:** Initial pre-release of the app.

