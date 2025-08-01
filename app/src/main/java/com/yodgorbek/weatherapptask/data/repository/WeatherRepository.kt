package com.yodgorbek.weatherapptask.data.repository

import com.yodgorbek.weatherapptask.data.util.NetworkResponse
import com.yodgorbek.weatherapptask.domain.model.CurrentWeather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCurrentWeather(cityName: String): Flow<NetworkResponse<CurrentWeather>>
}