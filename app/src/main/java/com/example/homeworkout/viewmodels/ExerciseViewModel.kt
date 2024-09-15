package com.example.homeworkout.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.homeworkout.models.Exercise
import androidx.lifecycle.viewModelScope
import com.example.homeworkout.models.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ExerciseViewModel @Inject constructor(private val repository: ExerciseRepository):ViewModel(){
    fun insertExercise(exercise: Exercise) = viewModelScope.launch {
        repository.insertExercise(exercise)
    }

    fun updateExercise(exercise: Exercise) = viewModelScope.launch {
        repository.updateExercise(exercise)
    }

    fun deleteExercise(exercise: Exercise) = viewModelScope.launch {
        repository.deleteExercise(exercise)
    }

    fun getExercises():LiveData<List<Exercise>> =
        repository.getAllExercises()
}
