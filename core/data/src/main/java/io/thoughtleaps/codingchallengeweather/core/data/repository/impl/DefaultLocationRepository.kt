package io.thoughtleaps.codingchallengeweather.core.data.repository.impl

import io.thoughtleaps.codingchallengeweather.core.common.network.Dispatcher
import io.thoughtleaps.codingchallengeweather.core.common.network.Dispatchers
import io.thoughtleaps.codingchallengeweather.core.data.model.toLocationModel
import io.thoughtleaps.codingchallengeweather.core.data.repository.LocationRepository
import io.thoughtleaps.codingchallengeweather.core.location.LocationProvider
import io.thoughtleaps.codingchallengeweather.core.model.LocationModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class DefaultLocationRepository @Inject constructor(
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val locationProvider: LocationProvider,
) : LocationRepository {

    override fun userLocation(): Flow<LocationModel> = flow {
        emit(locationProvider.getLastLocation().toLocationModel())
    }.flowOn(ioDispatcher)

}
