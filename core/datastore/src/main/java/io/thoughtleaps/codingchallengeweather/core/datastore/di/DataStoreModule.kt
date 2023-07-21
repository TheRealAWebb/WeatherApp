package io.thoughtleaps.codingchallengeweather.core.datastore.di
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.thoughtleaps.codingchallengeweather.core.datastore.UserPreferencesSerializer
import io.thoughtleaps.codingchallengeweather.core.common.network.Dispatcher
import io.thoughtleaps.codingchallengeweather.core.common.network.Dispatchers
import io.thoughtleaps.codingchallengeweather.core.common.network.di.ApplicationScope
import io.thoughtleaps.codingchallengeweather.core.datastore.UserPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providesUserPreferencesDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(Dispatchers.IO) ioDispatcher: CoroutineDispatcher,
        @ApplicationScope scope: CoroutineScope,
        userPreferencesSerializer: UserPreferencesSerializer,
    ): DataStore<UserPreferences> =
        DataStoreFactory.create(
            serializer = userPreferencesSerializer,
            scope = CoroutineScope(scope.coroutineContext + ioDispatcher),

            ) {
            context.dataStoreFile("user_preferences.pb")
        }
}
