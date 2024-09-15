package com.example.homeworkout.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.homeworkout.models.GraphModel

@Dao
interface GraphDao {

    @Insert
    suspend fun insertGraphEntries(entries: GraphModel)

    @Update
    suspend fun updateGraphEntries(entries: GraphModel)

    @Delete
    suspend fun deleteEntries(entries:GraphModel)

    @Query("SELECT * FROM graph_model")
    fun getAllEntries(): LiveData<List<GraphModel>>
}