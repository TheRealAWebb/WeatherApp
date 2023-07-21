package io.thoughtleaps.codingchallengeweather.core.data.repository

import io.thoughtleaps.codingchallengeweather.core.model.GeoModel
import io.thoughtleaps.codingchallengeweather.core.model.UserDataModel
import io.thoughtleaps.codingchallengeweather.core.model.WeatherModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCurrentWeather(
        user: UserDataModel,
    ): Flow<WeatherModel>


    /**Direct geocoding allows to get geographical coordinates (lat, lon) by using name of
     * the location (city name or area name). If you use the limit parameter in the API call,
     * you can cap how many locations with the same name will be seen in the API response
     * (for instance, London in the UK and London in the US).
       https://openweathermap.org/api/geocoding-api#direct

     **/
    fun getDirectGeocoding(
        cityName: String,
    ): Flow<List<GeoModel>>

    fun getReverseGeocoding(
        latitude: Double,
        longitude: Double,
    ):  Flow<List<GeoModel>>
}
