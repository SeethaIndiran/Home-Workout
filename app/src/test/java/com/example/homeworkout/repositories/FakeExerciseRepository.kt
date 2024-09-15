package com.example.homeworkout.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.homeworkout.models.Exercise
import com.example.homeworkout.models.ExerciseRepository

class FakeExerciseRepository:ExerciseRepository {

    private val exercisesList = mutableListOf<Exercise>()
    private val observableExercisesList = MutableLiveData<List<Exercise>>(exercisesList)


    private fun refreshLiveData(){
        observableExercisesList.postValue(exercisesList)
    }

    override suspend fun insertExercise(exercise: Exercise) {
               exercisesList.add(exercise)
                  refreshLiveData()
    }

    override suspend fun updateExercise(exercise: Exercise) {

    }

    override suspend fun deleteExercise(exercise: Exercise) {
             exercisesList.remove(exercise)
               refreshLiveData()
    }

    override fun getAllExercises(): LiveData<List<Exercise>> {
       return observableExercisesList
    }
}