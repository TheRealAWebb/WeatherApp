package io.thoughtleaps.codingchallengeweather.core.network

import FakeAssetManager
import JvmUnitTestFakeAssetManager
import io.thoughtleaps.codingchallengeweather.core.common.network.Dispatcher
import io.thoughtleaps.codingchallengeweather.core.common.network.Dispatchers.IO
import io.thoughtleaps.codingchallengeweather.core.network.model.GeoDirectResponseModel
import io.thoughtleaps.codingchallengeweather.core.network.model.OpenWeatherResponseModel
import io.thoughtleaps.codingchallengeweather.core.network.retrofit.NetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

class TestNetworkDataSource @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: FakeAssetManager = JvmUnitTestFakeAssetManager,
) : NetworkDataSource {
    override suspend fun getCurrentWeather(cityName: String): OpenWeatherResponseModel =
        withContext(ioDispatcher) {
            assets.open(WEATHER_RESPONSE_ASSET).use(networkJson::decodeFromStream)
        }


    override suspend fun getCurrentWeatherByLocation(
        latitude: Double, longitude: Double
    ): OpenWeatherResponseModel = withContext(ioDispatcher) {
        assets.open(WEATHER_RESPONSE_ASSET).use(networkJson::decodeFromStream)
    }


    override suspend fun getDirectGeocoding(cityName: String): List<GeoDirectResponseModel> =

        withContext(ioDispatcher) {
            assets.open(GEO_RESPONSE_ASSET).use(networkJson::decodeFromStream)
        }


    override suspend fun getReverseGeocoding(
        latitude: Double, longitude: Double
    ): List<GeoDirectResponseModel> = withContext(ioDispatcher) {
        assets.open(GEO_RESPONSE_ASSET).use(networkJson::decodeFromStream)
    }


    companion object {
        private const val WEATHER_RESPONSE_ASSET = "weather.json"
        private const val GEO_RESPONSE_ASSET = "geocode.json"
    }
}