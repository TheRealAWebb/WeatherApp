package io.thoughtleaps.codingchallengeweather.core.network

import JvmUnitTestFakeAssetManager
import io.thoughtleaps.codingchallengeweather.core.network.model.Coord
import io.thoughtleaps.codingchallengeweather.core.network.model.GeoDirectResponseModel
import io.thoughtleaps.codingchallengeweather.core.network.model.Main
import io.thoughtleaps.codingchallengeweather.core.network.model.OpenWeatherResponseModel
import io.thoughtleaps.codingchallengeweather.core.network.model.Weather
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class TestNetworkDataSourceTest {

    private lateinit var subject: TestNetworkDataSource
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        subject = TestNetworkDataSource(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true },
            assets = JvmUnitTestFakeAssetManager,
        )
    }

    @Test
    fun testDeserializationGeoDirectResponseModel() = runTest(testDispatcher) {
        assertEquals(
            GeoDirectResponseModel(
                name = "Queens County",
                latitude = 40.7135078,
                longitude = -73.8283132,
                state = "New York"
            ),
            subject.getDirectGeocoding("").first(),
        )
    }

    @Test
    fun testDeserializationOfOpenWeatherResponseModel() = runTest(testDispatcher) {
        assertEquals(
            OpenWeatherResponseModel(
                main = Main(
                    temp = 83.71,
                    feelsLike = 88.11,
                    tempMin = 77.61,
                    tempMax = 88.72,
                    pressure = 1013.toDouble(),
                    humidity = 64.toDouble(),
                ),
                coord = Coord(
                    longitude = -73.9662,
                    latitude = 40.7834,
                ),
                weather = listOf(
                    Weather(
                        id = 721,
                        main = "Haze",
                        description = "haze",
                        icon = "50d"
                    )
                ),
                name = "New York County"
            ),
            subject.getCurrentWeather(""),
        )
    }
}
