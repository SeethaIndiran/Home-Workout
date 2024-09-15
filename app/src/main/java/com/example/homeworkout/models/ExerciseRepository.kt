package com.example.homeworkout.models

import androidx.lifecycle.LiveData

interface ExerciseRepository {

    suspend fun insertExercise(exercise: Exercise)

    suspend fun updateExercise(exercise: Exercise)

    suspend fun deleteExercise(exercise: Exercise)

    fun getAllExercises(): LiveData<List<Exercise>>
}