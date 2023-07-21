package io.thoughtleaps.codingchallengeweather.features.search

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import io.thoughtleaps.codingchallengeweather.core.model.GeoModel

@Composable
internal fun SearchRoute(
    modifier: Modifier = Modifier,
    onComplete: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val searchResultUiState by viewModel.searchResultUiState.collectAsStateWithLifecycle()
    val locationResultUiState by viewModel.locationResultUiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    LocationPermissionEffect()
    SearchScreen(
        modifier = modifier,
        onComplete = onComplete,
        onSearchResultClicked = viewModel::onSearchResultClicked,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onUseMyLocationClicked = viewModel::onUseMyLocationClicked,
        searchQuery = searchQuery,
        searchResultUiState = searchResultUiState,
        locationResultUiState = locationResultUiState,
    )
}

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    onComplete: () -> Unit = {},
    onSearchResultClicked: (GeoModel) -> Unit = {},
    onSearchQueryChanged: (String) -> Unit = {},
    onSearchTriggered: (String) -> Unit = {},
    onUseMyLocationClicked: () -> Unit = {},
    searchQuery: String = "",
    searchResultUiState: SearchResultUiState = SearchResultUiState.Loading,
    locationResultUiState: LocationResultUiState = LocationResultUiState.Loading,
) {
    Column(modifier = modifier) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        SearchToolbar(
            onUseMyLocationClicked = onUseMyLocationClicked,
            onSearchQueryChanged = onSearchQueryChanged,
            onSearchTriggered = onSearchTriggered,
            searchQuery = searchQuery,
        )
        when (locationResultUiState) {
            is LocationResultUiState.Loading, LocationResultUiState.LoadFailed -> Unit
            is LocationResultUiState.EmptyQuery ->  Unit
            is LocationResultUiState.Success -> {
                if (locationResultUiState.isEmpty()) {
                } else {
                    SearchResultBody(
                        onComplete,
                        onSearchResultClicked,
                        locationResultUiState.searchResults
                    )
                }
            }
        }

        when (searchResultUiState) {
            is SearchResultUiState.Loading, SearchResultUiState.LoadFailed -> Unit
            is SearchResultUiState.EmptyQuery -> Unit
            is SearchResultUiState.Success -> {
                if (searchResultUiState.isEmpty()) {
                    EmptySearchResultBody(searchQuery = searchQuery)
                }else{
                    SearchResultBody(
                        onComplete,
                        onSearchResultClicked,
                        searchResultUiState.searchResults
                    )
                }
            }
        }
        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
    }
}


@Composable
private fun SearchToolbar(
    modifier: Modifier = Modifier,
    onUseMyLocationClicked: () -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    searchQuery: String = "",
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        IconButton(
            onClick = onUseMyLocationClicked,
            modifier = Modifier
                .padding()
                .then(Modifier.size(dimensionResource(R.dimen.location_icon_size)))
                .then(Modifier.align(alignment = Alignment.CenterVertically)),

            ) {
            Icon(
                painter = painterResource(android.R.drawable.ic_menu_mylocation),
                contentDescription = stringResource(R.string.current_location_icon)
            )
        }

        SearchTextField(
            onSearchQueryChanged = onSearchQueryChanged,
            searchQuery = searchQuery,
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchTextField(
    onSearchQueryChanged: (String) -> Unit,
    searchQuery: String,
) {
    val focusRequester = remember { FocusRequester() }

    TextField(
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = stringResource(
                    id = R.string.search,
                ),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(
                    onClick = {
                        onSearchQueryChanged("")
                    },
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = stringResource(
                            id = R.string.clear_search_text_content_desc,
                        ),
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        onValueChange = {
            onSearchQueryChanged(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .focusRequester(focusRequester)
            .testTag(stringResource(id = R.string.search_text_field_tag)),
        shape = RoundedCornerShape(32.dp),
        value = searchQuery,
        maxLines = 1,
        singleLine = true,
    )
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}


@Composable
fun EmptySearchResultBody(
    searchQuery: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 48.dp),
    ) {
        val message = stringResource(id = R.string.search_result_not_found, searchQuery)
        val start = message.indexOf(searchQuery)
        Text(
            text = AnnotatedString(
                text = message,
                spanStyles = listOf(
                    AnnotatedString.Range(
                        SpanStyle(fontWeight = FontWeight.Bold),
                        start = start,
                        end = start + searchQuery.length,
                    ),
                ),
            ),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 24.dp),
        )

        Text(
            text = stringResource(R.string.try_another_search),
            style = MaterialTheme.typography.bodyLarge.merge(
                TextStyle(
                    textAlign = TextAlign.Center,
                ),
            ),
            modifier = Modifier
                .padding(start = 36.dp, end = 36.dp, bottom = 24.dp)
                .clickable {},
        )
    }
}

@Composable
private fun SearchResultBody(
    onComplete: () -> Unit,
    onSearchResultClicked: (GeoModel) -> Unit,
    recentSearchQueries: List<GeoModel>,
) {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(stringResource(id = R.string.results_format))
                    }
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            )
        }
        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            items(recentSearchQueries) { recentSearch ->
                Text(
                    text = stringResource(
                        id = R.string.search_results,
                        recentSearch.city,
                        recentSearch.state
                    ),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .clickable {
                            onSearchResultClicked(recentSearch)
                            onComplete()
                        }
                        .fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalPermissionsApi::class)
private fun LocationPermissionEffect() {
    if (LocalInspectionMode.current) return
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return
    val locationPermissionState = rememberMultiplePermissionsState(
        listOf(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION),
    )
    LaunchedEffect(locationPermissionState) {
        if (!locationPermissionState.allPermissionsGranted && !locationPermissionState.shouldShowRationale) {
            locationPermissionState.launchMultiplePermissionRequest()
        }
    }
}
