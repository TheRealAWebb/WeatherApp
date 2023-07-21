package io.thoughtleaps.codingchallengeweather.core.model

data class WeatherModel(
    val temp: Double,
    val pressure: Double,
    val humidity: Double,
    val tempMin: Double,
    val tempMax: Double,
    val imageUrl: String,
    val city: String,
    val state: String,
)