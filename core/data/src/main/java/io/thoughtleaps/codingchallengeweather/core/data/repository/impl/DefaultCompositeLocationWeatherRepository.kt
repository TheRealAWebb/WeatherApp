package io.thoughtleaps.codingchallengeweather.core.data.repository.impl

import io.thoughtleaps.codingchallengeweather.core.data.repository.CompositeLocationWeatherRepository
import io.thoughtleaps.codingchallengeweather.core.data.repository.LocationRepository
import io.thoughtleaps.codingchallengeweather.core.data.repository.WeatherRepository
import io.thoughtleaps.codingchallengeweather.core.model.GeoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class DefaultCompositeLocationWeatherRepository @Inject constructor(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository,
) : CompositeLocationWeatherRepository {

    override fun getWeatherAtCurrentLocation(): Flow<List<GeoModel>> {
        return locationRepository.userLocation().flatMapLatest { location ->
            weatherRepository.getReverseGeocoding(location.latitude, location.longitude)
        }
    }
}
