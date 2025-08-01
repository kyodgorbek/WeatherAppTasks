🌦️ Mini Weather Dashboard App
📋 Overview
This is a simple Android application that fetches and displays weather data for a user-specified city using the OpenWeatherMap API.
The app is built using Clean Architecture, Jetpack Compose, Koin, Ktor, and DataStore, and includes several bonus features:

✅ Loading state
✅ Weather icon display
✅ Last searched city persistence

🏗️ Architecture
The app follows Clean Architecture, separating concerns across three layers:

Domain Layer

Contains business logic:

CurrentWeather model

GetCurrentWeatherUseCase

Data Layer

Handles external data:

API interaction via WeatherApi

Implementation in WeatherRepositoryImpl

Persistence with PreferencesDataStore

Presentation Layer

UI & state management using Jetpack Compose:

MainActivity

WeatherViewModel

🧰 Tech Stack
Technology	Purpose
Jetpack Compose	Modern, declarative UI toolkit with reactive capabilities
Koin	is a Lightweight DI framework, simple and suitable for small projects
Ktor	Kotlin-native HTTP client for network requests and JSON serialization
DataStore: Modern data storage solution, type-safe and coroutine-friendly
Coil	Efficient image loading for displaying weather icons
MockK: A Mocking library for unit testing Kotlin classes
Turbine	Tool for testing Kotlin Flows

🌐 API
Used OpenWeatherMap’s Current Weather Data API
The 
API key is hardcoded for simplicity, as allowed in the assignment

In production, sensitive keys should be stored securely using BuildConfig, encrypted storage, or environment variables.

⚠️ Error Handling
Displays user-friendly messages for:

Network errors (e.g., no internet)

Invalid city names (e.g., “City not found”)

