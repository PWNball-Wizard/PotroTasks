package com.potro.potrotasks.presentation.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.potro.potrotasks.AppDatabase.TodoDao
import com.potro.potrotasks.MainActivity
import com.potro.potrotasks.domain.model.Task
import com.potro.potrotasks.domain.model.service.StorageService
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel: ViewModel() {
    private val dao = MainActivity.database.todoDao()
    private var scope = MainScope()
    private val _tasksForDate = MutableStateFlow<List<Task>>(emptyList())
    // Exponer como un Flow público para que la UI pueda colectarlo.
    val tasksForDate: Flow<List<Task>> = _tasksForDate.asStateFlow()

    fun insertTask(task: Task) {
        scope.launch {
            dao.insert(task)
        }
    }

    val tasks: StateFlow<List<Task>> = dao.getAll()
        .stateIn(
            scope = viewModelScope, // viewModelScope está vinculado al ciclo de vida del ViewModel
            started = SharingStarted.Lazily, // Comienza perezosamente cuando hay observadores
            initialValue = emptyList() // Valor inicial antes de que se reciban los datos de la base de datos
        )

    fun deleteTask(task: Task) {
        scope.launch {
            dao.delete(task)
        }
    }

    fun updateTask(task: Task) {
        scope.launch {
            dao.update(task)
        }
    }

    /*fun getTasksByDate(date: String): Flow<List<Task>> {
        return dao.getTasksByDate(date)
    }*/

    fun getTasksByDate(date: String): Flow<List<Task>> = dao.getTasksByDate(date)

    fun updateTasksForDate(date: String) {
       viewModelScope.launch {
           _tasksForDate.value = dao.getTasksByDate(date).first()
       }
    // Actualiza el Flow basado en la nueva fecha
        //_tasksForDate = getTasksByDate(date)
    }
}