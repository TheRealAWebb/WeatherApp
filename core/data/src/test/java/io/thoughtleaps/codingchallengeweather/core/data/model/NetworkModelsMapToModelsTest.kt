package io.thoughtleaps.codingchallengeweather.core.data.model

import io.thoughtleaps.codingchallengeweather.core.network.BuildConfig
import io.thoughtleaps.codingchallengeweather.core.network.model.Coord
import io.thoughtleaps.codingchallengeweather.core.network.model.GeoDirectResponseModel
import io.thoughtleaps.codingchallengeweather.core.network.model.Main
import io.thoughtleaps.codingchallengeweather.core.network.model.OpenWeatherResponseModel
import io.thoughtleaps.codingchallengeweather.core.network.model.Weather
import org.junit.Test
import kotlin.test.assertEquals

internal class NetworkModelsMapToModelsTest {

    @Test
    fun geo_direct_responsemodel_can_be_mapped_to_geo_model() {
        val nameValue = "City Name"
        val longitudeValue = 101.toDouble()
        val latitudeValue = 202.toDouble()
        val stateValue = "State Name"

        val responseModel = GeoDirectResponseModel(
            name = nameValue,
            longitude = longitudeValue,
            latitude = latitudeValue,
            state = stateValue,
        )

        val entity = responseModel.toGeoDirectModel()

        assertEquals(nameValue, entity.city)
        assertEquals(latitudeValue, entity.latitude)
        assertEquals(longitudeValue, entity.longitude)
        assertEquals(stateValue, entity.state)
    }


    // State does not map to the new model as it it not returned int the URL
    @Test
    fun weather_responsemodel_can_be_mapped_to_weather_model() {
        val temp = 83.71
        val pressure = 1013.toDouble()
        val humidity = 64.toDouble()
        val tempMin = 77.61
        val tempMax = 88.72
        val imageUrl = BuildConfig.ICON_URL.format("weather[0].icon")
        val name = "City Name"

        val responseModel = OpenWeatherResponseModel(
            main = Main(
                temp = temp,
                feelsLike = 88.11,
                tempMin = tempMin,
                tempMax = tempMax,
                pressure = pressure,
                humidity = humidity,
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
            name = name
        )


        val entity = responseModel.toWeatherModel()

        assertEquals(temp, entity.temp)
        assertEquals(pressure, entity.pressure)
        assertEquals(humidity, entity.humidity)
        assertEquals(tempMin, entity.tempMin)
        assertEquals(tempMax, entity.tempMax)
        assertEquals(imageUrl, entity.imageUrl)
        assertEquals(name, entity.city)
        assertEquals("", entity.state)

    }


}