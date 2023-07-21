package io.thoughtleaps.codingchallengeweather.core.data.model

import io.thoughtleaps.codingchallengeweather.core.model.GeoModel
import io.thoughtleaps.codingchallengeweather.core.network.model.GeoDirectResponseModel

fun GeoDirectResponseModel.toGeoDirectModel(): GeoModel = GeoModel(
    city = name,
    longitude = longitude,
    latitude = latitude,
    state = state,
    )