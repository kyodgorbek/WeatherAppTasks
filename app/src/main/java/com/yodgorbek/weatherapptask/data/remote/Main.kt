package com.yodgorbek.weatherapptask.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class Main(
    val temp: Double
)