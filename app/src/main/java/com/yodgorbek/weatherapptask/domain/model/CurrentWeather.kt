package com.yodgorbek.weatherapptask.domain.model
data class CurrentWeather(
    val cityName: String,
    val temperature: Double, // In Celsius
    val weatherCondition: String, // e.g., "Clouds", "Rain"
    val weatherIcon: String? // Icon code, e.g., "10d"
)