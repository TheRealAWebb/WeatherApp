package io.thoughtleaps.codingchallengeweather.core.data.repository

import io.thoughtleaps.codingchallengeweather.core.model.WeatherModel
import kotlinx.coroutines.flow.Flow

interface CompositeUserWeatherRepository {

    fun getUserCurrentWeather(): Flow<WeatherModel>

}