package com.example.homeworkout.fragments

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.example.homeworkout.R
import com.example.homeworkout.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@HiltAndroidTest
class TrainingFragmentTest{

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp(){
        hiltRule.inject()
        
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun clickRecyclerViewItem_navigateToWorkoutTypeFragment(){
        val navController = mock(NavController::class.java)

        val bundle = Bundle().apply {
            putInt("workout",0)
            putString("player","players")
        }

        launchFragmentInHiltContainer<TrainingFragment> {
            Navigation.setViewNavController(requireView(),navController)
        }
        onView(withId(R.id.cl_layout)).perform(click())

        verify(navController).navigate(TrainingFragmentDirections.actionTrainingFragmentToWorkoutTypeFragment(0,"player"))

    }
}