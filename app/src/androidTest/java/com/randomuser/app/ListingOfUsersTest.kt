package com.randomuser.app

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.fragment.app.testing.launchFragmentInContainer
import com.randomuser.app.fragments.list.ListingOfUsersFragment

@RunWith(AndroidJUnit4::class)
class ListingOfUsersTest {

    // Does not work as Hilt requires an activity with @AndroidEntryPoint
    @Test fun testListingOfUsersFragment() {
        val scenario = launchFragmentInContainer<ListingOfUsersFragment>()
    }
}