package com.example.homeworkout.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "workouts_list")
data class WorkoutsList (
    val week_number:Int,
    val currentDate: String,
    val firstDate:String,
    val seventhDate:String,
    val num_workouts:Int,
    var total_duration:Int,
    var total_calories:Int,
    var num_workouts_list:MutableList<Exercise>,
    @PrimaryKey(autoGenerate = true) val id:Int = 0
        ): Serializable