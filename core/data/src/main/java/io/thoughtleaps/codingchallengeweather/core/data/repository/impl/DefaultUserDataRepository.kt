package io.thoughtleaps.codingchallengeweather.core.data.repository.impl

import io.thoughtleaps.codingchallengeweather.core.data.repository.UserDataRepository
import io.thoughtleaps.codingchallengeweather.core.datastore.PreferencesDataSource
import io.thoughtleaps.codingchallengeweather.core.model.GeoModel
import io.thoughtleaps.codingchallengeweather.core.model.UserDataModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultUserDataRepository @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource
) : UserDataRepository {

    override val userData: Flow<UserDataModel> = preferencesDataSource.userData

    override suspend fun setSearchWeather(model: GeoModel) = preferencesDataSource.setSearchWeather(model)
}