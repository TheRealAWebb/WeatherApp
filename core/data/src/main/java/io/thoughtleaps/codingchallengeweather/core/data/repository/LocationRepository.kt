package io.thoughtleaps.codingchallengeweather.core.data.repository

import io.thoughtleaps.codingchallengeweather.core.model.LocationModel
import kotlinx.coroutines.flow.Flow


interface LocationRepository {

   fun userLocation(): Flow<LocationModel>
}
