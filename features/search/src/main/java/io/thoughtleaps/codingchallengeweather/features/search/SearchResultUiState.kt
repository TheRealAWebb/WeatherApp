package io.thoughtleaps.codingchallengeweather.features.search

import io.thoughtleaps.codingchallengeweather.core.model.GeoModel

sealed interface SearchResultUiState {
    object Loading : SearchResultUiState

    object EmptyQuery : SearchResultUiState

    object LoadFailed : SearchResultUiState

    data class Success(
        val searchResults: List<GeoModel> = emptyList(),
    ) : SearchResultUiState {
        fun isEmpty(): Boolean = searchResults.isEmpty()
    }
}
