package io.thoughtleaps.codingchallengeweather.core.network.retrofit

import io.thoughtleaps.codingchallengeweather.core.network.model.GeoDirectResponseModel
import io.thoughtleaps.codingchallengeweather.core.network.model.OpenWeatherResponseModel

interface NetworkDataSource {
    suspend fun getCurrentWeather(
        cityName: String,
    ): OpenWeatherResponseModel

    suspend fun getCurrentWeatherByLocation(
        latitude: Double,
        longitude: Double
    ): OpenWeatherResponseModel

    suspend fun getDirectGeocoding(
        cityName: String,
    ): List<GeoDirectResponseModel>

    suspend fun getReverseGeocoding(
        latitude: Double,
        longitude: Double,
    ): List<GeoDirectResponseModel>
}