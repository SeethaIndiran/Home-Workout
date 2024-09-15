package com.example.homeworkout.models



import java.io.Serializable

data class Workout (
    val name:String,
    val image:Int,
    val imageDB:Int,
    val type:ArrayList<WorkoutType>
): Serializable