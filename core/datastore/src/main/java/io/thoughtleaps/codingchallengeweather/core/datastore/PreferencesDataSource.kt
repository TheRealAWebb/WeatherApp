package io.thoughtleaps.codingchallengeweather.core.datastore


import androidx.datastore.core.DataStore
import io.thoughtleaps.codingchallengeweather.core.model.GeoModel
import io.thoughtleaps.codingchallengeweather.core.model.UserDataModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>,
) {
    val userData = userPreferences.data
        .map {
            UserDataModel(
                city = it.city,
                state = it.state,
                latitude = it.latitude,
                longitude = it.longitude,
            )
        }

    suspend fun setSearchWeather(geoDirect: GeoModel) {
        userPreferences.updateData {
            it.copy {
                this.city = geoDirect.city
                this.state = geoDirect.state
                this.latitude = geoDirect.latitude
                this.longitude = geoDirect.longitude
            }
        }
    }
}
