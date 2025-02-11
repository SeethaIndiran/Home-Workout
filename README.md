# Home Workout App

## Overview
   The Home Workout App is designed to to help users track their workouts, monitor progress, and stay motivated. The app provides a calendar-based workout tracker, exercise timer, and BMI/weight tracking charts, making it an all-in-one solution for fitness enthusiasts. It follows MVVM architecture and integrates Dagger Hilt, Coroutines, and ViewModel to ensure a robust and scalable codebase.

## Features
### 📅 Workout Tracking with Calendar View

   * Users can log daily and weekly workout sessions.
   * Workouts are displayed on a CalendarView, visually representing the user's workout history.
   * Users can view past workout sessions and stay consistent with their fitness goals.

### ⏳ Exercise Timer & Workout Routine
   * A built-in exercise timer helps users track workout durations.
   * The app provides guided exercises with animations to assist users in maintaining proper form.
   * Workouts can be categorized based on types like cardio, strength training, or yoga.

### 📊 Progress Tracking with MPAndroid Charts
   * The app uses MPAndroidChart to display BMI and weight trends over time.
   * Users can analyze their fitness journey using line charts and pie charts for detailed insights.
   * Charts update dynamically as users log their progress.

### 🏋️ Exercise Animations using Lottie
   * Lottie animations provide visual guidance for workouts.
   * Engaging and interactive animations improve user experience and encourage consistency.

### 📖 Workout History in RecyclerView
   * A RecyclerView displays the history of completed workouts.
   * Users can scroll through their past sessions and analyze trends.

### ⚙️ MVVM Architecture for Clean Code
   * Uses MVVM (Model-View-ViewModel) to ensure a clean and maintainable architecture.
   * Separates concerns between UI, business logic, and data handling.

### 🚀 Dagger Hilt for Dependency Injection
   * Dagger Hilt is used for dependency injection, making the app modular and scalable.

### 🔄 Coroutines for Efficient Background Processing
   * Uses Kotlin Coroutines to handle background tasks like saving workouts, fetching data, and updating UI smoothly.

### 🏆 User-Friendly UI with ConstraintLayout
   * A well-designed UI using ConstraintLayout for seamless adaptability across different screen sizes.
   * A CollapsingToolbarLayout enhances the aesthetic appeal.

## Tech Stack
  * Language: Kotlin
  * Architecture: MVVM (Model-View-ViewModel)
  * Dependency Injection: Dagger Hilt
  * Networking: Retrofit with Coroutines
  * Data Storage: Room Database, SharedPreferences
  * Charts and Graphs: MP Android Charts
  * UI Components: XML Layouts, ConstraintLayout, Material Design
  * Asynchronous Handling: Kotlin Coroutines and Flow
  * Testing: JUnit, Mockito, Espresso

## Installation
Clone the repository:

1. git clone https://github.com/yourusername/home-workout-app.git
2. Open the project in Android Studio
3. Sync Gradle and build the project
4. Run the app on an emulator or a physical device

## Running Tests
###  Unit Tests (JUnit, Mockito)
   Unit Tests verify the functionalities of Viewmoels, Repositiories and other componenets.
   
   To run Unit Tests:
  
   1. Open Terminal in Android Studio
   
   2. Execute ./gradlew test
       
###  Instrumented UI Tests (Espresso, Hilt Testing)
    
   
   To run Instrumented Tests:
  
   1. Connect  an Android Emulator or physical device
   
   2. Execute ./gradlew connectedAndroidTest

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

## Contact

For questions or feedback, contact:
Email: seethaindhiran@gmail.com
GitHub: SeethaIndiran

## Screen shots

![image alt](https://github.com/SeethaIndiran/Home-Workout/blob/050d0bb587c8149b525f948534d6a7e7a882f23d/1%5B2%5D.png)
![image alt](https://github.com/SeethaIndiran/Home-Workout/blob/39c7ec4eb2f3677881b97292ab3ca2480dcd5dcf/2%5B1%5D.png)
![image alt](https://github.com/SeethaIndiran/Home-Workout/blob/6bf147f8b6f167455d1b6776fd28dee656f85f16/3%5B1%5D.png)
![image alt](https://github.com/SeethaIndiran/Home-Workout/blob/4539842c765ada18965be8c7f80515d039b76002/4%5B1%5D.png)
![image alt](https://github.com/SeethaIndiran/Home-Workout/blob/7a8c1499d5fa8db24bfedeb80c6f5304bce1f52d/5%5B1%5D.png)
