package io.thoughtleaps.codingchallengeweather.core.data.model

import android.location.Location
import io.thoughtleaps.codingchallengeweather.core.model.LocationModel


fun Location.toLocationModel(): LocationModel = LocationModel(
    latitude = latitude,
    longitude = longitude
)


