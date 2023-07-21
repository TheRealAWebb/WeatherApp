package io.thoughtleaps.codingchallengeweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import io.thoughtleaps.codingchallengeweather.navigation.WeatherNavHost
import io.thoughtleaps.codingchallengeweather.ui.MainActivityViewModel
import io.thoughtleaps.codingchallengeweather.ui.theme.CodingChallengeWeatherTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodingChallengeWeatherTheme {
                val shouldShowSearch by viewModel.shouldShowSearch.collectAsStateWithLifecycle()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherNavHost(shouldShowSearch)
                }
            }
        }
    }
}
