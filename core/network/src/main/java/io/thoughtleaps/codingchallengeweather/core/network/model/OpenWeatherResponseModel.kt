package io.thoughtleaps.codingchallengeweather.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class OpenWeatherResponseModel(
    val main: Main,
    val coord: Coord,
    val weather: List<Weather>,
    val name: String,
)

@Serializable
data class Main(
    val temp: Double,
    @SerialName("feels_like")
    val feelsLike: Double,
    val pressure: Double,
    val humidity: Double,
    @SerialName("temp_min")
    val tempMin: Double,
    @SerialName("temp_max")
    val tempMax: Double,
)

@Serializable
data class Coord(
    @SerialName("lon")
    val longitude: Double,
    @SerialName("lat")
    val latitude: Double,
)


@Serializable
data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String,
)
