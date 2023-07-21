package io.thoughtleaps.codingchallengeweather.core.data.repository

import io.thoughtleaps.codingchallengeweather.core.model.GeoModel
import kotlinx.coroutines.flow.Flow

interface CompositeLocationWeatherRepository {
    fun getWeatherAtCurrentLocation():  Flow<List<GeoModel>>
}