🌦️ Mini Weather Dashboard App

📋 Overview
This is a simple Android application that fetches and displays weather data for a user-specified city using the OpenWeatherMap API.

Built using Clean Architecture, this project leverages modern tools and libraries:

Jetpack Compose for the UI

Koin for Dependency Injection

Ktor for network communication

DataStore for data persistence

✅ Bonus Features
Loading state with indicator

Weather icon display

Persistence of the last searched city

🏗️ Architecture
The app follows Clean Architecture principles, dividing responsibilities across three layers:

🧠 Domain Layer
Contains business logic

CurrentWeather model

GetCurrentWeatherUseCase

📡 Data Layer
Handles external data operations:

API interaction via WeatherApi

Repository implementation in WeatherRepositoryImpl

Persistence with PreferencesDataStore

🎨 Presentation Layer
Manages UI and user interactions using Jetpack Compose

MainActivity

WeatherViewModel

🧰 Tech Stack
Technology	Purpose
Jetpack Compose	Modern, declarative UI toolkit with reactive capabilities
Koin	Lightweight dependency injection framework, ideal for small projects
Ktor	Kotlin-native HTTP client for network calls and JSON serialization
DataStore	Type-safe, coroutine-friendly modern data storage solution
Coil	Efficient image loading for displaying weather icons
MockK	Powerful mocking framework for Kotlin unit tests
Turbine	Tool for testing Kotlin Flows

🌐 API
Uses OpenWeatherMap’s Current Weather Data API

The API key is hardcoded as allowed for the assignment

⚠️ Important: In production, secrets like API keys should be stored securely:

BuildConfig

Encrypted storage

Environment variables (CI/CD)

⚠️ Error Handling
The app displays user-friendly messages for:

❌ Network errors (e.g., no internet connection)

❌ Invalid city names (e.g., “City not found”)
