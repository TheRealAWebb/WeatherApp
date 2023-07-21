package io.thoughtleaps.codingchallengeweather.core.data.repository

import io.thoughtleaps.codingchallengeweather.core.model.GeoModel
import io.thoughtleaps.codingchallengeweather.core.model.UserDataModel
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    /**
     * Stream of [UserDataModel]
     */
    val userData: Flow<UserDataModel>


    /**
     * Sets weather the user has searched.
     */
    suspend fun setSearchWeather(model: GeoModel)
}