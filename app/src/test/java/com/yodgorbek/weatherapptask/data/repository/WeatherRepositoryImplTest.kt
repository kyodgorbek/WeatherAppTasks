package com.yodgorbek.weatherapptask.data.repository

import app.cash.turbine.test
import com.yodgorbek.weatherapptask.data.remote.Main
import com.yodgorbek.weatherapptask.data.remote.Weather
import com.yodgorbek.weatherapptask.data.remote.WeatherApi
import com.yodgorbek.weatherapptask.data.remote.WeatherResponse
import com.yodgorbek.weatherapptask.data.util.NetworkResponse
import com.yodgorbek.weatherapptask.domain.model.CurrentWeather
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRepositoryImplTest {

    private lateinit var weatherApi: WeatherApi
    private lateinit var repository: WeatherRepositoryImpl

    @Before
    fun setup() {
        weatherApi = mockk()
        repository = WeatherRepositoryImpl(weatherApi)
    }



    @Test
    fun `getCurrentWeather emits Error on IOException`() = runTest {
        val cityName = "Nowhere"

        coEvery { weatherApi.getCurrentWeather(cityName) } throws IOException("Network failure")

        repository.getCurrentWeather(cityName).test {
            assertEquals(NetworkResponse.Loading, awaitItem())
            val result = awaitItem()
            assert(result is NetworkResponse.Error)
            assertEquals("Network error", (result as NetworkResponse.Error).message)
            awaitComplete()
        }
    }

    @Test
    fun `getCurrentWeather emits Error on Exception`() = runTest {
        val cityName = "Nowhere"

        coEvery { weatherApi.getCurrentWeather(cityName) } throws RuntimeException("City not found")

        repository.getCurrentWeather(cityName).test {
            assertEquals(NetworkResponse.Loading, awaitItem())
            val result = awaitItem()
            assert(result is NetworkResponse.Error)
            assertEquals("City not found", (result as NetworkResponse.Error).message)
            awaitComplete()
        }
    }
}
