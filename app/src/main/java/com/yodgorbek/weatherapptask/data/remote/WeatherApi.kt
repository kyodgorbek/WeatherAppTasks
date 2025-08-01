package com.yodgorbek.weatherapptask.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get


const val OPENWEATHERMAP_API_KEY = "cf3a3ae6b169e0c06e29b07a1faac584"

class WeatherApi(private val client: HttpClient) {
    suspend fun getCurrentWeather(cityName: String): WeatherResponse {
        return client.get(
            "https://api.openweathermap.org/data/2.5/weather?q=$cityName&appid=$OPENWEATHERMAP_API_KEY"
        ).body()
    }
}