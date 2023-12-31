package io.thoughtleaps.codingchallengeweather.features.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.thoughtleaps.codingchallengeweather.features.search.SearchRoute

const val searchRoute = "search_route"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(searchRoute, navOptions)
}

fun NavGraphBuilder.searchScreen(
    onComplete:() -> Unit,
) {
    // TODO: Handle back stack for each top-level destination. At the moment each top-level
    // destination may have own search screen's back stack.
    composable(route = searchRoute) {
        SearchRoute(
            onComplete = onComplete,
        )
    }
}
