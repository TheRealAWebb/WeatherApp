package io.thoughtleaps.codingchallengeweather.features.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.thoughtleaps.codingchallengeweather.core.common.result.Result.Error
import io.thoughtleaps.codingchallengeweather.core.common.result.Result.Loading
import io.thoughtleaps.codingchallengeweather.core.common.result.Result.Success
import io.thoughtleaps.codingchallengeweather.core.common.result.asResult
import io.thoughtleaps.codingchallengeweather.core.data.repository.LocationRepository
import io.thoughtleaps.codingchallengeweather.core.data.repository.UserDataRepository
import io.thoughtleaps.codingchallengeweather.core.data.repository.WeatherRepository
import io.thoughtleaps.codingchallengeweather.core.model.GeoModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val SEARCH_QUERY = "searchQuery"
private const val LOCATION_LATITUDE = "latitude"
private const val LOCATION_LONGITUDE = "longitude"
private const val DEBOUNCE_TIME = 1000L
private const val FILTER_WORD_MIN = 2

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val userDataRepository: UserDataRepository,
    private val savedStateHandle: SavedStateHandle,
    private val locationRepository: LocationRepository,
) : ViewModel() {

    val searchQuery = savedStateHandle.getStateFlow(SEARCH_QUERY, "")

    private val locationLatitude: StateFlow<Double?> =
        savedStateHandle.getStateFlow(LOCATION_LATITUDE, null)
    private val locationLon: StateFlow<Double?> =
        savedStateHandle.getStateFlow(LOCATION_LONGITUDE, null)

    val searchResultUiState: StateFlow<SearchResultUiState> = searchQuery
        // Used TO Throttle REQUEST
        .debounce(timeoutMillis = DEBOUNCE_TIME)
        .filter { it.length > FILTER_WORD_MIN }
        .flatMapLatest { query ->
            weatherRepository.getDirectGeocoding(query).asResult().map {
                when (it) {
                    is Success -> {
                        SearchResultUiState.Success(
                            searchResults = it.data
                        )
                    }

                    is Loading -> {
                        SearchResultUiState.Loading
                    }

                    is Error -> {
                        SearchResultUiState.LoadFailed
                    }
                }
            }
            //  }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SearchResultUiState.Loading,
        )


    val locationResultUiState: StateFlow<LocationResultUiState> =
        locationLatitude.zip(locationLon) { lat, lon ->
            Pair(
                lat,
                lon
            )
        }.flatMapLatest { query ->
            if (query.second == null || query.first == null) {
                return@flatMapLatest flowOf(LocationResultUiState.EmptyQuery)
            }
            weatherRepository.getReverseGeocoding(query.first!!, query.second!!).asResult()
                .map {
                    when (it) {
                        is Success -> {
                            LocationResultUiState.Success(
                                searchResults = it.data
                            )
                        }
                        is Loading -> {
                            LocationResultUiState.Loading
                        }
                        is Error -> {
                            LocationResultUiState.LoadFailed
                        }
                    }
                }

        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = LocationResultUiState.Loading,
        )


    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }

    fun onSearchResultClicked(model: GeoModel) {
        viewModelScope.launch {
            userDataRepository.setSearchWeather(model)
        }
    }

    fun onUseMyLocationClicked() {
        viewModelScope.launch {
            locationRepository.userLocation().asResult().collect {
                when (it) {
                    is Success -> {
                        savedStateHandle[LOCATION_LATITUDE] = it.data.latitude
                        savedStateHandle[LOCATION_LONGITUDE] = it.data.longitude
                    }

                    is Error -> {}
                    is Loading -> {}
                }
            }
        }
    }
}
