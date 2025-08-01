Mini Weather Dashboard App

Overview

This is a simple Android application that fetches and displays weather data for a user-specified city using the OpenWeatherMap API. 
The app is built with Clean Architecture, Jetpack Compose, Koin for dependency injection, Ktor for networking, and DataStore for persisting the last searched city. It fulfills all requirements and includes bonus features like a loading state, weather icon display, and persistence.

Architecture:





Used Clean Architecture with Domain, Data, and Presentation layers to ensure separation of concerns, testability, and maintainability.



Domain layer contains independent business logic (CurrentWeather model, GetCurrentWeatherUseCase).



Data layer handles API calls (WeatherApi, WeatherRepositoryImpl) and persistence (PreferencesDataStore).



Presentation layer manages UI (MainActivity, WeatherViewModel) with Jetpack Compose.



Tech Stack:





Jetpack Compose: Chosen for modern, declarative UI development, enabling a responsive and clean interface (bonus requirement).



Koin: Lightweight dependency injection framework, simpler than Hilt for a small app.



Ktor: Used for networking due to its Kotlin-native design and flexibility with JSON serialization.



DataStore: Implemented for persisting the last searched city (bonus requirement), preferred over SharedPreferences for type safety.



Coil: Used for loading weather icons from OpenWeatherMap (bonus requirement).



API:





Utilized OpenWeatherMap’s free Current Weather Data API for simplicity and reliability.



Hardcoded the API key in ApiKey.kt as permitted by the assignment. In production, I’d store it securely using BuildConfig or environment variables.



Error Handling:





Handles network failures and invalid city inputs, displaying user-friendly messages (e.g., "City not found or unexpected error").
