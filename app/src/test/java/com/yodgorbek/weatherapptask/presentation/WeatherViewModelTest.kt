package com.yodgorbek.weatherapptask.presentation

import app.cash.turbine.test
import com.yodgorbek.weatherapptask.data.local.PreferencesDataStore
import com.yodgorbek.weatherapptask.data.util.NetworkResponse
import com.yodgorbek.weatherapptask.domain.model.CurrentWeather
import com.yodgorbek.weatherapptask.domain.usecase.GetCurrentWeatherUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    private lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase
    private lateinit var preferencesDataStore: PreferencesDataStore
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getCurrentWeatherUseCase = mockk()
        preferencesDataStore = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun `getCurrentWeather does not fetch if city is blank`() = runTest {
        val blankCity = "   "

        coEvery { preferencesDataStore.lastCity } returns flowOf("")
        coEvery { preferencesDataStore.saveLastCity(any()) } returns Unit

        val viewModel = WeatherViewModel(getCurrentWeatherUseCase, preferencesDataStore)
        advanceUntilIdle()

        viewModel.weatherState.test {
            awaitItem() // From init block
            viewModel.getCurrentWeather(blankCity)
            expectNoEvents()
            cancelAndConsumeRemainingEvents()
        }

        coVerify(exactly = 0) { getCurrentWeatherUseCase(any()) }
        coVerify(exactly = 0) { preferencesDataStore.saveLastCity(any()) }
    }
}
