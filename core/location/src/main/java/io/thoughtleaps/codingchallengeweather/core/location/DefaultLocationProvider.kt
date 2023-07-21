package io.thoughtleaps.codingchallengeweather.core.location

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.tasks.asDeferred
import javax.inject.Inject


class DefaultLocationProvider @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
) : LocationProvider {


    @SuppressLint("MissingPermission")
    override suspend fun getLastLocation(): Location {
        return fusedLocationProviderClient.lastLocation.asDeferred().await()
    }
}
