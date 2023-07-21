package io.thoughtleaps.codingchallengeweather.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeoDirectResponseModel(
    val name: String,
    @SerialName("lon") val longitude: Double,
    @SerialName("lat") val latitude: Double,
    val state: String,
)
