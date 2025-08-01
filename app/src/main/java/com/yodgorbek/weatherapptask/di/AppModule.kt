package com.yodgorbek.weatherapptask.di

import com.yodgorbek.weatherapptask.data.local.PreferencesDataStore
import com.yodgorbek.weatherapptask.data.remote.WeatherApi
import com.yodgorbek.weatherapptask.data.repository.WeatherRepository
import com.yodgorbek.weatherapptask.data.repository.WeatherRepositoryImpl
import com.yodgorbek.weatherapptask.domain.usecase.GetCurrentWeatherUseCase
import com.yodgorbek.weatherapptask.presentation.WeatherViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<HttpClient> {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
            install(Logging)
        }
    }
    single { WeatherApi(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single { GetCurrentWeatherUseCase(get()) }
    single { PreferencesDataStore(get()) }
    viewModel { WeatherViewModel(get(), get()) }
}