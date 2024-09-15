package com.example.homeworkout.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.homeworkout.common.Converters
import com.example.homeworkout.models.Exercise
import com.example.homeworkout.models.GraphModel
import com.example.homeworkout.models.WorkoutsList

@Database(entities = [Exercise::class, GraphModel::class,  WorkoutsList::class], version = 1)
@TypeConverters(Converters::class)
abstract class ExerciseDatabase:RoomDatabase() {

    abstract fun getGraphDao():GraphDao
    abstract fun getExerciseDa0():ExerciseDao
    abstract fun getWorkoutsListDao():WorkoutsListDao
}