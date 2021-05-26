package com.mellagusty.movieapppopcorn

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.mellagusty.movieapppopcorn.resource.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idling_resource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idling_resource)
    }

    @Test
    fun getMovie() {

        //Click to the Movie Navigation
        onView(withId(R.id.navigation_movie)).perform(click())

        //Scroll Recycle View on Movie Nav
        onView(withId(R.id.rv_card)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1)
        )
        //Choose Movie Item on RV
        onView(withId(R.id.rv_card)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        //Testing the Details
        val tvLanguage = onView(withId(R.id.tv_language))
        tvLanguage.perform(ViewActions.scrollTo())
        tvLanguage.check(ViewAssertions.matches(isDisplayed()))

        val tvGenres = onView(withId(R.id.tv_genres))
        tvGenres.perform(ViewActions.scrollTo())
        tvGenres.check(ViewAssertions.matches(isDisplayed()))

        val tvOriginalName = onView(withId(R.id.tv_original_name))
        tvOriginalName.perform(ViewActions.scrollTo())
        tvOriginalName.check(ViewAssertions.matches(isDisplayed()))

        val tvOverview = onView(withId(R.id.tv_overview))
        tvOverview.perform(ViewActions.scrollTo())
        tvOverview.check(ViewAssertions.matches(isDisplayed()))

        val tvStatus = onView(withId(R.id.tv_status))
        tvStatus.perform(ViewActions.scrollTo())
        tvStatus.check(ViewAssertions.matches(isDisplayed()))

        val tvStar = onView(withId(R.id.tv_star))
        tvStar.perform(ViewActions.scrollTo())
        tvStar.check(ViewAssertions.matches(isDisplayed()))

    }

    @Test
    fun getTVShow() {
        //Click to the Movie Navigation
        onView(withId(R.id.navigation_TvShow)).perform(click())

        //Scroll Recycle View on Movie Nav
        onView(withId(R.id.rv_card)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1)
        )
        //Choose Movie Item on RV
        onView(withId(R.id.rv_card)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )

        //Testing the Details
        val tvLanguage = onView(withId(R.id.tv_language))
        tvLanguage.perform(ViewActions.scrollTo())
        tvLanguage.check(ViewAssertions.matches(isDisplayed()))

        val tvGenres = onView(withId(R.id.tv_genres))
        tvGenres.perform(ViewActions.scrollTo())
        tvGenres.check(ViewAssertions.matches(isDisplayed()))

        val tvEpisodes = onView(withId(R.id.tv_episode_total))
        tvEpisodes.perform(ViewActions.scrollTo())
        tvEpisodes.check(ViewAssertions.matches(isDisplayed()))

        val tvType = onView(withId(R.id.tv_type))
        tvType.perform(ViewActions.scrollTo())
        tvType.check(ViewAssertions.matches(isDisplayed()))

        val tvOriginalName = onView(withId(R.id.tv_original_name))
        tvOriginalName.perform(ViewActions.scrollTo())
        tvOriginalName.check(ViewAssertions.matches(isDisplayed()))

        val tvOverview = onView(withId(R.id.tv_overview))
        tvOverview.perform(ViewActions.scrollTo())
        tvOverview.check(ViewAssertions.matches(isDisplayed()))

        val tvStatus = onView(withId(R.id.tv_status))
        tvStatus.perform(ViewActions.scrollTo())
        tvStatus.check(ViewAssertions.matches(isDisplayed()))

        val tvStar = onView(withId(R.id.tv_star))
        tvStar.perform(ViewActions.scrollTo())
        tvStar.check(ViewAssertions.matches(isDisplayed()))

    }

    @Test
    fun getAddRemoveMovieFavorite() {
        //Click to the Movie Navigation
        onView(withId(R.id.navigation_movie)).perform(click())

        //Scroll Recycle View on Movie Nav
        onView(withId(R.id.rv_card)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1)
        )
        //Choose Movie Item on RV
        onView(withId(R.id.rv_card)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.toggle_favorite)).perform(click())
        pressBack()

        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.rv_favorite)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0)
        )
        //Choose Movie Item on RV
        onView(withId(R.id.rv_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.toggle_favorite)).perform(click())
        pressBack()


    }

    @Test
    fun getAddRemoveTVFavorite(){
        //Click to the Movie Navigation
        onView(withId(R.id.navigation_TvShow)).perform(click())

        //Scroll Recycle View on Movie Nav
        onView(withId(R.id.rv_card)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1)
        )
        //Choose Movie Item on RV
        onView(withId(R.id.rv_card)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.toggle_favorite)).perform(click())
        pressBack()

        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.rv_favorite)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0)
        )
        //Choose Movie Item on RV
        onView(withId(R.id.rv_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.toggle_favorite)).perform(click())
        pressBack()


    }

    

}