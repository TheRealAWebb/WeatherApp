package io.thoughtleaps.codingchallengeweather.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.thoughtleaps.codingchallengeweather.features.search.navigation.navigateToSearch
import io.thoughtleaps.codingchallengeweather.features.search.navigation.searchRoute
import io.thoughtleaps.codingchallengeweather.features.search.navigation.searchScreen
import io.thoughtleaps.codingchallengeweather.features.weather.navigation.navigateToWeather
import io.thoughtleaps.codingchallengeweather.features.weather.navigation.weatherNavigationRoute
import io.thoughtleaps.codingchallengeweather.features.weather.navigation.weatherScreen

@Composable
fun WeatherNavHost(
    shouldShowSearch: Boolean
) {
    val navController = rememberNavController()
    val startDestination = if (shouldShowSearch) {
        searchRoute
    } else {
        weatherNavigationRoute
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier,
    ) {

        weatherScreen {
            navController.navigateToSearch(
                NavOptions.Builder()
                    .build()

            )
        }
        searchScreen {
            navController.navigateToWeather(
                NavOptions.Builder()
                    .setPopUpTo(searchRoute,true)
                    .setLaunchSingleTop(true)
                    .build()
            )
        }
    }
}
