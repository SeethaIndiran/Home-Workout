package com.example.homeworkout.repositories

import androidx.lifecycle.LiveData
import com.example.homeworkout.db.ExerciseDao
import com.example.homeworkout.models.Exercise
import com.example.homeworkout.models.ExerciseRepository
import javax.inject.Inject


class ExerciseRepositoryImpl @Inject constructor(private val dao: ExerciseDao):ExerciseRepository {
    override suspend fun insertExercise(exercise: Exercise) {
        dao.insertExercise(exercise)
    }

    override suspend fun updateExercise(exercise: Exercise) {
        dao.updateExercise(exercise)
    }

    override suspend fun deleteExercise(exercise: Exercise) {
        dao.deleteExercise(exercise)
    }

    override fun getAllExercises(): LiveData<List<Exercise>> {
       return dao.getAllExercisesForSameWeek()
    }

}