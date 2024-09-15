package com.example.homeworkout.models

import androidx.lifecycle.LiveData

interface GraphRepository {

    suspend fun insertGraphEntries(entries: GraphModel)

    suspend fun updateGraphEntries(entries: GraphModel)

    suspend fun deleteEntry(entries:GraphModel)

    fun getAllEntries(): LiveData<List<GraphModel>>
}