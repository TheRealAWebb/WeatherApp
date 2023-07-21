package io.thoughtleaps.codingchallengeweather.features.weather

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import io.thoughtleaps.codingchallengeweather.core.testing.data.currentWeather
import io.thoughtleaps.codingchallengeweather.core.testing.data.error
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class WeatherScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var loading: String
    private lateinit var errors: String
    private lateinit var weatherResult: String
    private lateinit var searchButtonTag: String

    @Before
    fun setup() {
        composeTestRule.activity.apply {
            composeTestRule.activity.apply {
                loading = getString(R.string.loading)
                errors = getString(R.string.error, error)
                weatherResult = getString(
                    R.string.the_temp_in_city,
                    currentWeather.city,
                    currentWeather.state,
                    currentWeather.temp
                )
                searchButtonTag = getString(R.string.search_button_tag)
            }
        }
    }

    @Test
    fun weatherStateSuccess_show_the_weather_data() {
        composeTestRule.setContent {
            WeatherScreen(WeatherUiState.Success(currentWeather))
        }

        composeTestRule
            .onNodeWithText(weatherResult)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithTag(searchButtonTag)
            .assertIsDisplayed()

    }

    @Test
    fun weatherStateLoading_show_loading() {
        composeTestRule.setContent {
            WeatherScreen(WeatherUiState.Loading)
        }

        composeTestRule
            .onNodeWithText(loading)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(searchButtonTag)
            .assertIsDisplayed()
    }

    @Test
    fun weatherStateError_show_an_error() {
        composeTestRule.setContent {
            WeatherScreen(WeatherUiState.Error(error))
        }

        composeTestRule
            .onNodeWithText(errors)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(searchButtonTag)
            .assertIsDisplayed()
    }


    @Test
    fun weatherStateError_button_is_displayed() {
        composeTestRule.setContent {
            WeatherScreen()
        }

        composeTestRule
            .onNodeWithTag(searchButtonTag)
            .assertIsDisplayed()
    }
}
