package io.thoughtleaps.codingchallengeweather.core.data.model

import io.thoughtleaps.codingchallengeweather.core.model.WeatherModel
import io.thoughtleaps.codingchallengeweather.core.network.BuildConfig
import io.thoughtleaps.codingchallengeweather.core.network.model.OpenWeatherResponseModel

fun OpenWeatherResponseModel.toWeatherModel(): WeatherModel = WeatherModel(
    temp = main.temp,
    pressure = main.pressure,
    humidity = main.humidity,
    tempMin = main.tempMin,
    tempMax = main.tempMax,
    imageUrl = BuildConfig.ICON_URL.format(weather[0].icon),
    city = name,
    state = "",
)
