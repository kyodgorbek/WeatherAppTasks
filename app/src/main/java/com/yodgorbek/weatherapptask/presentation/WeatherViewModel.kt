package com.yodgorbek.weatherapptask.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yodgorbek.weatherapptask.data.local.PreferencesDataStore
import com.yodgorbek.weatherapptask.data.util.NetworkResponse
import com.yodgorbek.weatherapptask.domain.model.CurrentWeather
import com.yodgorbek.weatherapptask.domain.usecase.GetCurrentWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val useCase: GetCurrentWeatherUseCase,
    private val preferences: PreferencesDataStore
) : ViewModel() {
    private val _weatherState = MutableStateFlow<NetworkResponse<CurrentWeather>>(NetworkResponse.Loading)
    val weatherState: StateFlow<NetworkResponse<CurrentWeather>> = _weatherState


    var cityName : String by mutableStateOf("")
        private set

    fun localName(name: String?) {
        cityName = name.orEmpty()
    }

    init {
        viewModelScope.launch {
            cityName = preferences.lastCity.first() ?: "London"
            getCurrentWeather(cityName)
        }
    }

    fun getCurrentWeather(cityName: String) {
        viewModelScope.launch {
            if (cityName.isNotBlank()) {
                localName(cityName)
                preferences.saveLastCity(cityName)
                useCase(cityName).collect { response ->
                    _weatherState.value = response
                }
            }
        }
    }
}