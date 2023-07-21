package io.thoughtleaps.codingchallengeweather.features.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.thoughtleaps.codingchallengeweather.core.data.repository.CompositeUserWeatherRepository
import io.thoughtleaps.codingchallengeweather.core.model.WeatherModel
import io.thoughtleaps.codingchallengeweather.features.weather.WeatherUiState.Loading
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import io.thoughtleaps.codingchallengeweather.features.weather.WeatherUiState.Error as Errors


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val compositeUserWeatherRepository: CompositeUserWeatherRepository,
    ) : ViewModel() {
    val feedUiState: StateFlow<WeatherUiState> = compositeUserWeatherRepository.getUserCurrentWeather()
        .map<WeatherModel, WeatherUiState>(WeatherUiState::Success)
        .catch { error -> emit(Errors(error.message.toString())) }
        .onStart { emit(Loading) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Loading,
        )
}
