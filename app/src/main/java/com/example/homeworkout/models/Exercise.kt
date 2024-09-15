package com.example.homeworkout.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "exercise_table")
data class Exercise (
    val name:String,
    val image:Int,
    val weekNumber:Int,
    val date:String,
    val time:String,
    val duration:Int,
    val calories:Int,
    @PrimaryKey(autoGenerate = true) val id:Int = 0
    ):Serializable