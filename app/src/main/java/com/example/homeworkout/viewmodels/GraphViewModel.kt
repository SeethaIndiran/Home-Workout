package com.example.homeworkout.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworkout.models.GraphModel
import com.example.homeworkout.models.GraphRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GraphViewModel @Inject constructor(private val repository: GraphRepository):ViewModel(){
    fun insertGraphEntries(entries: GraphModel) = viewModelScope.launch {
        repository.insertGraphEntries(entries)
    }

    fun updateGraphEntries(entries: GraphModel) = viewModelScope.launch {
        repository.updateGraphEntries(entries)
    }

    fun deleteEntry(entries:GraphModel) = viewModelScope.launch {
        repository.deleteEntry(entries)
    }

    fun getAllEntries() = repository.getAllEntries()
}