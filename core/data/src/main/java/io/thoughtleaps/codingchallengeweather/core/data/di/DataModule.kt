package io.thoughtleaps.codingchallengeweather.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.thoughtleaps.codingchallengeweather.core.data.repository.CompositeLocationWeatherRepository
import io.thoughtleaps.codingchallengeweather.core.data.repository.CompositeUserWeatherRepository
import io.thoughtleaps.codingchallengeweather.core.data.repository.LocationRepository
import io.thoughtleaps.codingchallengeweather.core.data.repository.UserDataRepository
import io.thoughtleaps.codingchallengeweather.core.data.repository.WeatherRepository
import io.thoughtleaps.codingchallengeweather.core.data.repository.impl.DefaultCompositeLocationWeatherRepository
import io.thoughtleaps.codingchallengeweather.core.data.repository.impl.DefaultCompositeUserWeatherRepository
import io.thoughtleaps.codingchallengeweather.core.data.repository.impl.DefaultLocationRepository
import io.thoughtleaps.codingchallengeweather.core.data.repository.impl.DefaultUserDataRepository
import io.thoughtleaps.codingchallengeweather.core.data.repository.impl.DefaultWeatherRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsWeatherRepository(
        weatherRepository: DefaultWeatherRepository,
    ): WeatherRepository

    @Binds
    fun bindsUserDataRepository(
        userDataRepository: DefaultUserDataRepository,
    ): UserDataRepository

    @Binds
    fun bindsLocationRepository(
        userDataRepository: DefaultLocationRepository,
    ): LocationRepository

    @Binds
    fun bindsCompositeLocationWeatherRepositoryy(
        defaultCompositeLocationWeatherRepository: DefaultCompositeLocationWeatherRepository,
    ): CompositeLocationWeatherRepository
    @Binds
    fun bindsCompositeUserWeatherRepository(
        defaultCompositeUserWeatherRepository: DefaultCompositeUserWeatherRepository,
    ): CompositeUserWeatherRepository
}