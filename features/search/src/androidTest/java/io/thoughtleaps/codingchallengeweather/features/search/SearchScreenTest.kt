package io.thoughtleaps.codingchallengeweather.features.search

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import io.thoughtleaps.codingchallengeweather.core.testing.data.geoSearchResultsTestData
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class SearchScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var tryAnotherSearchString: String
    private lateinit var cityState: String
    private lateinit var cityStateJoined: List<String>
    private lateinit var searchTextTag: String

    @Before
    fun setup() {
        composeTestRule.activity.apply {
            tryAnotherSearchString = getString(R.string.try_another_search)
            cityState = getString(R.string.results_format)
            cityStateJoined = geoSearchResultsTestData.map {
                getString(R.string.search_results, it.city, it.state)
            }
            searchTextTag = getString(R.string.search_text_field_tag)

        }
    }


    @Test
    fun searchTextField_isFocused() {
        composeTestRule.setContent {
            SearchScreen()
        }

        composeTestRule
            .onNodeWithTag(searchTextTag)
            .assertIsFocused()
    }

    @Test
    fun emptySearchResult_emptyScreenIsDisplayed() {
        composeTestRule.setContent {
            SearchScreen(
                searchResultUiState = SearchResultUiState.Success()
            )
        }

        composeTestRule
            .onNodeWithText(tryAnotherSearchString)
            .assertIsDisplayed()
    }


    @Test
    fun successSearchResult_ScreenIsDisplayed() {
        composeTestRule.setContent {
            SearchScreen(
                searchResultUiState = SearchResultUiState.Success(geoSearchResultsTestData),
            )
        }

        composeTestRule
            .onNodeWithText(cityState)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(cityStateJoined[0])
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(cityStateJoined[1])
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(cityStateJoined[2])
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(cityStateJoined[3])
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(cityStateJoined[4])
            .assertIsDisplayed()
    }
}
