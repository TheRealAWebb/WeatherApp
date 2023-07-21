package io.thoughtleaps.codingchallengeweather.core.common.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.thoughtleaps.codingchallengeweather.core.common.network.Dispatcher
import io.thoughtleaps.codingchallengeweather.core.common.network.Dispatchers.Default
import io.thoughtleaps.codingchallengeweather.core.common.network.Dispatchers.IO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Dispatcher(IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(Default)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
