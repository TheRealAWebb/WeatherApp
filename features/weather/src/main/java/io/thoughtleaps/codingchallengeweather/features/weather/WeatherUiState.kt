package io.thoughtleaps.codingchallengeweather.features.weather

import io.thoughtleaps.codingchallengeweather.core.model.WeatherModel

sealed interface WeatherUiState {
    object Loading : WeatherUiState
    data class Success(val value: WeatherModel) : WeatherUiState
    data class Error(val message: String) : WeatherUiState
}
