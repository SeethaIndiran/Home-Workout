package com.example.homeworkout.models

import androidx.lifecycle.LiveData

interface WorkoutsListRepository {

    suspend fun insertWorkoutsList(list:WorkoutsList)

    suspend fun updateWorkoutsList(list:WorkoutsList)

    suspend fun deleteWorkoutsList(list:WorkoutsList)

    fun getAllWorkoutsList(): LiveData<List<WorkoutsList>>

    fun getWorkoutByWeek(weekNumber:Int):LiveData<WorkoutsList>
}