# 🏋️‍♂️Home Workout App

## 📌Overview
     The Workout Tracker App is a native Android application built using Kotlin that helps users log their workouts, track progress over time, 
     and visualize fitness data using interactive charts. It includes 
     features like a workout history, calendar tracking, exercise timer, 
     BMI and weight monitoring, and smooth animations for an engaging user experience.

## 🎯 Features
     ✅ Track Workouts: Log exercises, repetitions, and duration for each session.
     ✅ Calendar View: View and manage daily workout progress.
     ✅ Exercise Timer: Built-in timer to track workout duration.
     ✅ Workout History: Review past workouts in a RecyclerView.
     ✅ BMI & Weight Tracking: Monitor fitness progress with interactive MPAndroid Charts.
     ✅ Data Persistence: Store workout logs using Room Database for offline access.
     ✅ User Preferences: Save settings such as unit preferences with SharedPreferences.
     ✅ Modern UI: Uses Material Design, ConstraintLayout, and animations for a smooth experience.
     ✅ Lottie Animations: Engaging UI animations to enhance user interaction.

## 🛠 Tech Stack
     * Language: Kotlin
     * Architecture: MVVM (Model-View-ViewModel)
     * Dependency Injection: Dagger Hilt
     * Networking: Retrofit with Coroutines
     * Data Storage: Room Database, SharedPreferences
     * UI Components: Jetpack Compose, ConstraintLayout, Material Design
     * Asynchronous Handling: Kotlin Coroutines and Flow
     * Charts & Visualization: MPAndroid Charts
     * Testing: JUnit, Mockito, Espresso

## 📂 Project Structure
      ├── data
      │   ├── local (Room Database for offline storage)
      │   ├── repository (Handles data operations)
      ├── di (Dagger Hilt modules for dependency injection)
      ├── ui
      │   ├── home (Main workout dashboard)
      │   ├── calendar (Workout tracking calendar)
      │   ├── history (Workout history display)
      │   ├── bmi (BMI and weight tracking with charts)
      ├── utils (Helper functions, extensions, and constants)
      └── viewmodel (ViewModels for UI logic)

## 🚀 Installation and Setup
       1️⃣ Clone the repository
       2️⃣ Open the project in Android Studio
       3️⃣ Sync Gradle and build the project
       4️⃣ Run the app on an emulator or physical device

## 🧪 Testing
       * The app includes unit tests and UI tests.
       * Unit Testing: ViewModel, Repository, and Database tested with JUnit and Mockito.
       *  UI Testing: Fragment navigation and user interactions tested using Espresso.
       Run tests using:
       ./gradlew testDebugUnitTest
       ./gradlew connectedAndroidTest

## 🤝 Contributing
      Contributions are welcome! Follow these steps to contribute:

     1️⃣ Fork the repository
     2️⃣ Create a feature branch
     3️⃣ Commit your changes
     4️⃣ Push to the branch
     5️⃣ Open a Pull Request

## 📧 Contact
     For questions or feedback, contact:
    📩 Email: seethaindhiran@gmail.com
    👨‍💻 GitHub: yourusername

## Screenshots

![image alt](https://github.com/SeethaIndiran/Home-Workout/blob/050d0bb587c8149b525f948534d6a7e7a882f23d/1%5B2%5D.png)
![image alt](https://github.com/SeethaIndiran/Home-Workout/blob/39c7ec4eb2f3677881b97292ab3ca2480dcd5dcf/2%5B1%5D.png)
![image alt](https://github.com/SeethaIndiran/Home-Workout/blob/6bf147f8b6f167455d1b6776fd28dee656f85f16/3%5B1%5D.png)
![image alt](https://github.com/SeethaIndiran/Home-Workout/blob/4539842c765ada18965be8c7f80515d039b76002/4%5B1%5D.png)
![image alt](https://github.com/SeethaIndiran/Home-Workout/blob/7a8c1499d5fa8db24bfedeb80c6f5304bce1f52d/5%5B1%5D.png)
