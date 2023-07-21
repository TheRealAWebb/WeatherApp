package io.thoughtleaps.codingchallengeweather.core.datastore

import io.thoughtleaps.codingchallengeweather.core.datastore.di.testUserPreferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import kotlin.test.assertEquals

class PreferencesDataSourceTest {

    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var subject: PreferencesDataSource

    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @Before
    fun setup() {
        subject = PreferencesDataSource(
            tmpFolder.testUserPreferencesDataStore(testScope),
        )
    }

    @Test
    fun cityIsEmptyByDefault() = testScope.runTest {
        assertEquals(subject.userData.first().city,"")
    }
}
