package com.potro.potrotasks.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime
import java.util.Date

@Entity(tableName = "todos")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val taskName: String,
    //val created_at:Date = Date(),
    val category: String,
    val taskIsFinished: Boolean = false,
    val taskDate: LocalDate, // Añadido para almacenar la fecha
    val taskTime: LocalTime  // Añadido para almacenar la hora
){
    //val taskDescription: String,
    /*var taskName : String? = null,
    var taskDescription : String? = null,
    var taskDueDate : String? = null,
    var taskStartDate : String? = null,
    var taskHourStart : String? = null,
    var taskHourEnd : String? = null,
    var taskIsFinished : Boolean? = null,
    var taskCategory : String? = null,
    /*var taskName : String? = null,
    var taskDescription : String? = null,
    var taskDueDate : String? = null,
    var taskIsFinished : Boolean? = null,
    var taskID : String? = null,*/
){
    fun toMap():MutableMap<String, Any> {
        return mutableMapOf(
            "taskName" to this.taskName!!,
            "taskDescription" to this.taskDescription!!,
            "taskDueDate" to this.taskDueDate!!,
            "taskStartDate" to this.taskStartDate!!,
            "taskHourStart" to this.taskHourStart!!,
            "taskHourEnd" to this.taskHourEnd!!,
            "taskIsFinished" to this.taskIsFinished!!,
            "taskCategory" to this.taskCategory!!,
        )
    }

    companion object {
        // Aquí puedes definir funciones o propiedades estáticas
        fun exampleStaticFunction(): String {
            return "Esta es una función estática"
        }
    }*/
}