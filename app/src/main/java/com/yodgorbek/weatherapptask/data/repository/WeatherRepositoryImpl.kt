package com.yodgorbek.weatherapptask.data.repository

import com.yodgorbek.weatherapptask.data.remote.WeatherApi
import com.yodgorbek.weatherapptask.data.util.NetworkResponse
import com.yodgorbek.weatherapptask.domain.model.CurrentWeather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
class WeatherRepositoryImpl(private val api: WeatherApi) : WeatherRepository {
    override fun getCurrentWeather(cityName: String): Flow<NetworkResponse<CurrentWeather>> = flow {
        emit(NetworkResponse.Loading)
        try {
            val response = api.getCurrentWeather(cityName)
            val weather = CurrentWeather(
                cityName = response.name,
                temperature = response.main.temp - 273.15, // Convert Kelvin to Celsius
                weatherCondition = response.weather.firstOrNull()?.main ?: "Unknown",
                weatherIcon = response.weather.firstOrNull()?.icon
            )
            emit(NetworkResponse.Success(weather))
        } catch (e: IOException) {
            emit(NetworkResponse.Error("Network error"))
        } catch (e: Exception) {
            emit(NetworkResponse.Error("City not found"))
        }
    }
}