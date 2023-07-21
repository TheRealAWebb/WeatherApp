package io.thoughtleaps.codingchallengeweather.core.repositories

import io.thoughtleaps.codingchallengeweather.core.data.repository.WeatherRepository
import io.thoughtleaps.codingchallengeweather.core.model.GeoModel
import io.thoughtleaps.codingchallengeweather.core.model.UserDataModel
import io.thoughtleaps.codingchallengeweather.core.model.WeatherModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestWeatherRepository :WeatherRepository {

    /**
     * The backing hot flow for the list of topics ids for testing.
     */
    private val geoModelFlow: MutableSharedFlow<List<GeoModel>> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val weatherModelsFlow: MutableSharedFlow<List<WeatherModel>> = MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)


    override fun getCurrentWeather(user: UserDataModel): Flow<WeatherModel> {
        TODO("Not yet implemented")
    }

    override fun getDirectGeocoding(cityName: String): Flow<List<GeoModel>> {
        TODO("Not yet implemented")
    }

    override fun getReverseGeocoding(
        latitude: Double,
        longitude: Double
    ): Flow<List<GeoModel>> {
        TODO("Not yet implemented")
    }
}