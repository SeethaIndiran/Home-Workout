package com.example.homeworkout.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.homeworkout.models.Exercise

@Dao
interface ExerciseDao {
    @Insert
    suspend fun insertExercise(exercise:Exercise)

    @Update
    suspend fun updateExercise(exercise:Exercise)

    @Delete
    suspend fun deleteExercise(exercise:Exercise)

    @Query("SELECT *  FROM  exercise_table ")
    fun getAllExercisesForSameWeek():LiveData<List<Exercise>>
}