package com.yodgorbek.weatherapptask.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.yodgorbek.weatherapptask.data.util.NetworkResponse
import com.yodgorbek.weatherapptask.domain.model.CurrentWeather
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = koinViewModel()) {
    val weatherState by viewModel.weatherState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = viewModel.cityName,
            onValueChange = viewModel::localName,
            label = { Text("City Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        when (val state = weatherState) {
            is NetworkResponse.Loading -> CircularProgressIndicator()
            is NetworkResponse.Success ->{
                Spacer(modifier = Modifier.height(16.dp))
                WeatherCard(state.data)
            }
            is NetworkResponse.Error -> Text(
                modifier = Modifier.align(Alignment.Start),
                text = state.message,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.getCurrentWeather(viewModel.cityName) },
            enabled = viewModel.cityName.isNotBlank()
        ) {
            Text("Search")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun WeatherCard(weather: CurrentWeather) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = weather.cityName,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "${weather.temperature.roundTo(1)}°C",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = weather.weatherCondition,
                style = MaterialTheme.typography.bodyLarge
            )
            weather.weatherIcon?.let { icon ->
                Image(
                    painter = rememberAsyncImagePainter("https://openweathermap.org/img/wn/$icon@2x.png"),
                    contentDescription = "Weather Icon",
                    modifier = Modifier.size(64.dp)
                )
            }
        }
    }
}

fun Double.roundTo(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}