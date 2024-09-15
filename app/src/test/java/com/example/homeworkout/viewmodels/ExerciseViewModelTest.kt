package com.example.homeworkout.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.homeworkout.getOrAwaitValueTest
import com.example.homeworkout.models.Exercise
import com.example.homeworkout.repositories.FakeExerciseRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExerciseViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var exerciseViewModel: ExerciseViewModel
    private lateinit var repository:FakeExerciseRepository

    @Before
    fun setUp(){
        repository = FakeExerciseRepository()
        exerciseViewModel = ExerciseViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertExerciseToDB() = runBlockingTest {
        val exercise = Exercise("name",0,2,
            "date","time",2,3,0)
        repository.insertExercise(exercise)

        val list = repository.getAllExercises().getOrAwaitValueTest()
        assertThat(list).contains(exercise)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteExercise() = runBlockingTest {
        val exercise = Exercise("name",0,2,
            "date","time",2,3,0)
        repository.insertExercise(exercise)
        repository.deleteExercise(exercise)

        val list = repository.getAllExercises().getOrAwaitValueTest()
        assertThat(list).doesNotContain(exercise)
    }
}