package io.thoughtleaps.codingchallengeweather.features.search

import io.thoughtleaps.codingchallengeweather.core.model.GeoModel

sealed interface LocationResultUiState {
    object Loading : LocationResultUiState

    object EmptyQuery : LocationResultUiState

    object LoadFailed : LocationResultUiState

    data class Success(
        val searchResults: List<GeoModel> = emptyList(),
    ) : LocationResultUiState {
        fun isEmpty(): Boolean = searchResults.isEmpty()
    }
}