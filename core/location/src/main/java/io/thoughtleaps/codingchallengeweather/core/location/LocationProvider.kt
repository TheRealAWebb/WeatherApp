package io.thoughtleaps.codingchallengeweather.core.location

import android.location.Location

interface LocationProvider {
    suspend fun getLastLocation(): Location
}
