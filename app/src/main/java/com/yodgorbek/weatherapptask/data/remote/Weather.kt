package com.yodgorbek.weatherapptask.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    val main: String,
    val icon: String
)