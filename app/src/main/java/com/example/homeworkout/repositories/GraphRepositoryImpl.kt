package com.example.homeworkout.repositories

import androidx.lifecycle.LiveData
import com.example.homeworkout.db.GraphDao
import com.example.homeworkout.models.GraphModel
import com.example.homeworkout.models.GraphRepository
import javax.inject.Inject

class GraphRepositoryImpl @Inject constructor(private val graphDao:GraphDao):GraphRepository {
    override suspend fun insertGraphEntries(entries: GraphModel) {
        graphDao.insertGraphEntries(entries)
    }

    override suspend fun updateGraphEntries(entries: GraphModel) {
        graphDao.updateGraphEntries(entries)
    }

    override suspend fun deleteEntry(entries: GraphModel) {
        graphDao.deleteEntries(entries)
    }

    override fun getAllEntries(): LiveData<List<GraphModel>> {
       return graphDao.getAllEntries()
    }
}