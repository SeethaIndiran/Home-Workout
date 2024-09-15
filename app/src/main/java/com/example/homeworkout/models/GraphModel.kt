package com.example.homeworkout.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "graph_model")
data class GraphModel (
    var weight:Float,
    var lbWeight:Float,
    val date:String,
    @PrimaryKey(autoGenerate = true) val id:Int = 0
        )