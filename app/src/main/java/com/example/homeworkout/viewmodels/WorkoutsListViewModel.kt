package com.example.homeworkout.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworkout.models.WorkoutsList
import com.example.homeworkout.models.WorkoutsListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutsListViewModel @Inject constructor(private val repository: WorkoutsListRepository):ViewModel() {

    fun insertWorkoutsList(list:WorkoutsList) = viewModelScope.launch {
        repository.insertWorkoutsList(list)
    }

    fun updateWorkoutsList(list:WorkoutsList) = viewModelScope.launch {
        repository.updateWorkoutsList(list)
    }

    fun deleteWorkoutsList(list:WorkoutsList) = viewModelScope.launch {
        repository.deleteWorkoutsList(list)
    }

    fun getAllList() = repository.getAllWorkoutsList()

   fun getWorkoutByWeek(weekNumber:Int) = repository.getWorkoutByWeek(weekNumber)

}