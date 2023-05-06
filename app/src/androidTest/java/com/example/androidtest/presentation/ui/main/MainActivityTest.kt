package com.example.androidtest.presentation.ui.main

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.androidtest.R
import com.example.androidtest.presentation.ui.util.atPosition
import com.example.domain.models.trendingMovies.Movie
import com.example.domain.models.trendingMovies.TrendingMoviesResponse
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
internal class MainActivityTest {


    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    val moviesInTest = TrendingMoviesResponse(results = listOf(Movie(false ,"Any" , listOf(3 ,4) ,3245 ,"en" ,"First movie" ,"First Movie overview" ,23443.54 ,"Any" ,"10/03/2002" ,"Title First movie" ,null ,false ,132.4 ,4356),
        Movie(false ,"Any" , listOf(3 ,4) ,987 ,"ar" ,"Second movie" ,"Second Movie overview" ,8990.67 ,"Any" ,"10/03/2012" ,"Title second movie" ,null ,false ,87667.4 ,34534)))

    @Before
    fun before()
    {
        activityRule.scenario.onActivity {
            it.moviesAdapter.submitTrendingMoviesResponse(moviesInTest)
        }
    }

    @Test
    fun testIsListMoviesVisible()
    {
        onView(withId(R.id.textViewTitle)).check(matches(atPosition(0, hasDescendant(withText(moviesInTest.results!![0].title)))))
    }

}