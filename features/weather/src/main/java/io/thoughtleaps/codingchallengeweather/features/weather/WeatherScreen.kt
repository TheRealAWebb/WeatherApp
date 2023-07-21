package io.thoughtleaps.codingchallengeweather.features.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import io.thoughtleaps.codingchallengeweather.core.model.WeatherModel

@Composable
fun WeatherRoute(
    onSearchTap: () -> Unit = {},
    viewModel: WeatherViewModel = hiltViewModel(),
) {
    val feedState by viewModel.feedUiState.collectAsStateWithLifecycle()
    WeatherScreen(feedState, onSearchTap)

}

@Composable
internal fun WeatherScreen(
    feedState: WeatherUiState = WeatherUiState.Loading,
    onSearchTap: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        when (feedState) {
            is WeatherUiState.Success -> show(feedState.value)
            is WeatherUiState.Error -> Text(
                text = stringResource(
                    R.string.error,
                    feedState.message
                )
            )


            is WeatherUiState.Loading -> {
                Text(text = stringResource(R.string.loading))
            }
        }
        SearchButton(onSearchTap)
    }
}

@Composable
fun SearchButton(
    onSearchTap: () -> Unit = {}
) {
    val padding = dimensionResource(R.dimen.default_margin)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = padding, top = padding, end = padding),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        IconButton(
            onClick = onSearchTap,
            modifier = Modifier
                .padding()
                .then(Modifier.size(dimensionResource(R.dimen.location_icon_size)))
                .then(Modifier.align(alignment = Alignment.CenterVertically))
                .testTag(stringResource(id = R.string.search_button_tag)),
        ) {
            Icon(
                painter = painterResource(android.R.drawable.ic_search_category_default),
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = stringResource(R.string.search_icon)
            )
        }
    }


}


@Preview
@Composable
private fun showPreview() {
    show(
        WeatherModel(
            temp = 50.0,
            pressure = 10.0,
            humidity = 20.0,
            tempMin = 30.0,
            tempMax = 40.0,
            imageUrl = "",
            city = "This City",
            state = "This State",
        )

    )

}

@Composable
private fun show(
    model: WeatherModel,
    modifier: Modifier = Modifier,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = model.imageUrl,
            contentDescription = null,
            modifier = modifier
                .height(150.dp)
                .width(100.dp),
            placeholder = painterResource(R.drawable.ic_icon_placeholder),
        )
        Text(
            style = MaterialTheme.typography.headlineLarge,
            text = stringResource(
                R.string.the_temp_in_city,
                model.city,
                model.state,
                model.temp
            ),
        )
    }
}
