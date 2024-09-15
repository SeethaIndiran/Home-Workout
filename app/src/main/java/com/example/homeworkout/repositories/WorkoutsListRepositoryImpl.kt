package com.example.homeworkout.repositories

import androidx.lifecycle.LiveData
import com.example.homeworkout.db.WorkoutsListDao
import com.example.homeworkout.models.WorkoutsList
import com.example.homeworkout.models.WorkoutsListRepository
import javax.inject.Inject

class WorkoutsListRepositoryImpl @Inject constructor(private val dao:WorkoutsListDao):WorkoutsListRepository {
    override suspend fun insertWorkoutsList(list: WorkoutsList) {
        dao.insertWorkoutsList(list)
    }

    override suspend fun updateWorkoutsList(list: WorkoutsList) {
        dao.updateWorkoutsList(list)
    }

    override suspend fun deleteWorkoutsList(list: WorkoutsList) {
        dao.deleteWorkoutsList(list)
    }

    override fun getAllWorkoutsList(): LiveData<List<WorkoutsList>> {
       return dao.getAllWorkoutsList()
    }

    override fun getWorkoutByWeek(weekNumber: Int): LiveData<WorkoutsList> {
        return  dao.getWorkoutByWeek(weekNumber)
    }


}