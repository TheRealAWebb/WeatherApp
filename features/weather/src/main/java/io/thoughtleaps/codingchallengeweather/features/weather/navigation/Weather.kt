package io.thoughtleaps.codingchallengeweather.features.weather.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.thoughtleaps.codingchallengeweather.features.weather.WeatherRoute


const val weatherNavigationRoute = "weather_route"
fun NavController.navigateToWeather(navOptions: NavOptions? = null) {
    this.navigate(weatherNavigationRoute, navOptions)
}

fun NavGraphBuilder.weatherScreen(onCompleteTap: () -> Unit) {
    composable(
        route = weatherNavigationRoute,
        deepLinks = listOf(),
        arguments = listOf(),
    ) {
        WeatherRoute(onCompleteTap)
    }
}
