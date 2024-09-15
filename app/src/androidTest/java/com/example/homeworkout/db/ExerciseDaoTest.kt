package com.example.homeworkout.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.homeworkout.R
import com.example.homeworkout.getOrAwaitValue
import com.example.homeworkout.models.Exercise
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@SmallTest
@HiltAndroidTest
class ExerciseDaoTest {

    @get:Rule
    var hiltRule  = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

   @Inject
   @Named("test_db")
   lateinit var database:ExerciseDatabase
   private lateinit var dao:ExerciseDao

    @Before
    fun setUp(){
        hiltRule.inject()
        dao = database.getExerciseDa0()
    }

    @After
    fun teatDown(){
        database.close()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertExercise() = runBlockingTest {
        val exercise = Exercise("name", 1,
            1,"date","time",2,3,1)
        dao.insertExercise(exercise)

        val getAllExercises = dao.getAllExercisesForSameWeek().getOrAwaitValue()
        assertThat(getAllExercises).contains(exercise)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteExercise() = runBlockingTest {
        val exercise = Exercise("name", R.drawable.man,
            1,"date","time",2,3,0)
        dao.insertExercise(exercise)
        dao.deleteExercise(exercise)

        val getAllExercises = dao.getAllExercisesForSameWeek().getOrAwaitValue()
        assertThat(getAllExercises).doesNotContain(exercise)
    }
}