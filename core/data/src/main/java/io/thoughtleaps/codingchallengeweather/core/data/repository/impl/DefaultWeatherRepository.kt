package io.thoughtleaps.codingchallengeweather.core.data.repository.impl

import io.thoughtleaps.codingchallengeweather.core.common.network.Dispatcher
import io.thoughtleaps.codingchallengeweather.core.common.network.Dispatchers.IO
import io.thoughtleaps.codingchallengeweather.core.data.model.toGeoDirectModel
import io.thoughtleaps.codingchallengeweather.core.data.model.toWeatherModel
import io.thoughtleaps.codingchallengeweather.core.data.repository.WeatherRepository
import io.thoughtleaps.codingchallengeweather.core.model.GeoModel
import io.thoughtleaps.codingchallengeweather.core.model.UserDataModel
import io.thoughtleaps.codingchallengeweather.core.model.WeatherModel
import io.thoughtleaps.codingchallengeweather.core.network.model.GeoDirectResponseModel
import io.thoughtleaps.codingchallengeweather.core.network.retrofit.NetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


private const val SEARCH_VALUE = "%s,,US"

class DefaultWeatherRepository @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val network: NetworkDataSource,
) : WeatherRepository {

    override fun getCurrentWeather(user: UserDataModel): Flow<WeatherModel> = flow {
        emit(
            network.getCurrentWeatherByLocation(user.latitude, user.longitude).toWeatherModel()
                .copy(
                    state = user.state,
                )
        )
    }.flowOn(ioDispatcher)


    override fun getDirectGeocoding(cityName: String): Flow<List<GeoModel>> = flow {
        val searchParameter = SEARCH_VALUE.format(cityName.trim())
        emit(
            network.getDirectGeocoding(searchParameter)
                .map(GeoDirectResponseModel::toGeoDirectModel)
        )
    }.flowOn(ioDispatcher)

    override fun getReverseGeocoding(
        latitude: Double,
        longitude: Double
    ): Flow<List<GeoModel>> =
        flow {
            emit(
                network.getReverseGeocoding(latitude, longitude)
                    .map(GeoDirectResponseModel::toGeoDirectModel)
            )
        }.flowOn(ioDispatcher)
}
