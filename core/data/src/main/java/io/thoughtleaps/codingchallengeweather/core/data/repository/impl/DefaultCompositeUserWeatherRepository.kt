package io.thoughtleaps.codingchallengeweather.core.data.repository.impl

import io.thoughtleaps.codingchallengeweather.core.data.repository.CompositeUserWeatherRepository
import io.thoughtleaps.codingchallengeweather.core.data.repository.UserDataRepository
import io.thoughtleaps.codingchallengeweather.core.data.repository.WeatherRepository
import io.thoughtleaps.codingchallengeweather.core.model.WeatherModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class DefaultCompositeUserWeatherRepository @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val userDataRepository: UserDataRepository,
) : CompositeUserWeatherRepository {

    override fun getUserCurrentWeather(): Flow<WeatherModel> {
        return userDataRepository.userData.distinctUntilChanged()
            .flatMapLatest { data ->
                weatherRepository.getCurrentWeather(data)
            }
    }
}
