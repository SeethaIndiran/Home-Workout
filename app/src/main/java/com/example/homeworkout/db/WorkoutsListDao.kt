package com.example.homeworkout.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.homeworkout.models.WorkoutsList

@Dao
interface WorkoutsListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkoutsList(list:WorkoutsList)

    @Update
    suspend fun updateWorkoutsList(list:WorkoutsList)

    @Delete
    suspend fun deleteWorkoutsList(list:WorkoutsList)

    @Query("SELECT * FROM  workouts_list")
    fun getAllWorkoutsList(): LiveData<List<WorkoutsList>>

    @Query("SELECT * FROM workouts_list WHERE week_number =:weekNumber")
    fun getWorkoutByWeek(weekNumber:Int):LiveData<WorkoutsList>
}