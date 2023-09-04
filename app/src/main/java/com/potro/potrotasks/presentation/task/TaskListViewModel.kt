package com.potro.potrotasks.presentation.task

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.potro.potrotasks.data.TaskDataSource
import com.potro.potrotasks.domain.model.Task
import kotlinx.coroutines.launch

class TaskListViewModel : ViewModel() {

    private val tasks = mutableStateListOf(
        Task(taskName = "",
            taskDescription = "",
            taskID = "",
            taskDueDate = "",
            taskIsFinished = false)
    )

    init {
        updateTaskList()
    }

    private fun updateTaskList() {
        TaskDataSource.getTasks(tasks)
    }

    val deletedTasks = mutableStateListOf<String>()

    fun deleteTask(taskId : String){
        deletedTasks.add(taskId)
        TaskDataSource.deleteTask(taskId)
    }

    fun finishTask(taskId : String, isFinished : Boolean) = viewModelScope.launch {
        TaskDataSource.finishTask(taskId, isFinished)
    }

    fun getUnfinishedTasks() : List<Task> {
        val unfinishedTasks = mutableListOf<Task>()

        for(task in tasks)
            if(task.taskIsFinished == false)
                unfinishedTasks.add(task)

        return unfinishedTasks
    }

    fun getFinishedTasks() : List<Task> {
        val finishedTasks = mutableListOf<Task>()

        for(task in tasks)
            if(task.taskIsFinished == true)
                finishedTasks.add(task)

        return finishedTasks
    }

    fun getCurrentDateTask(date : String) : List<Task> {
        val currentDateTask = mutableListOf<Task>()

        for(task in tasks)
            if(task.taskDueDate == date && task.taskIsFinished == false)
                currentDateTask.add(task)

        return currentDateTask
    }

    fun checkIfDateHasEvent(date : String) : Int {
        return getCurrentDateTask(date).size
    }

}