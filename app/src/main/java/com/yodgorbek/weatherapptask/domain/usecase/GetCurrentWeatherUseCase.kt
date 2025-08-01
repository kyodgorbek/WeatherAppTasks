package com.yodgorbek.weatherapptask.domain.usecase

import com.yodgorbek.weatherapptask.data.repository.WeatherRepository
import com.yodgorbek.weatherapptask.data.util.NetworkResponse
import com.yodgorbek.weatherapptask.domain.model.CurrentWeather
import kotlinx.coroutines.flow.Flow

class GetCurrentWeatherUseCase(private val repository: WeatherRepository) {
    operator fun invoke(cityName: String): Flow<NetworkResponse<CurrentWeather>> {
        return repository.getCurrentWeather(cityName)
    }
}