package com.example.edumanage.viewModel

import androidx.lifecycle.*
import com.example.edumanage.model.Assignment
import com.example.edumanage.repository.AssignmentRepository
import kotlinx.coroutines.launch

class AssignmentsViewModel(private val repository: AssignmentRepository) : ViewModel() {

    private val _assignments = MutableLiveData<List<Assignment>>()
    val assignments: LiveData<List<Assignment>> get() = _assignments

    init {
        loadAssignments()
    }

    private fun loadAssignments() {
        viewModelScope.launch {
            try {
                val assignmentsList = repository.getAssignments()
                _assignments.value = assignmentsList
            } catch (e: Exception) {
                // Log error or handle it
                _assignments.value = emptyList()
            }
        }
    }
}

class AssignmentsViewModelFactory(
    private val repository: AssignmentRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssignmentsViewModel::class.java)) {
            return AssignmentsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
