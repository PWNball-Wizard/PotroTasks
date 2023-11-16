package com.potro.potrotasks.AppDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.potro.potrotasks.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos ORDER BY taskDate ASC")
    fun getAll(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)
    //para poder usar las funciones fuera en el viewmodel se debe hacer suspend
    //suspend fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    //update
    @Update
    suspend fun update(task: Task)

    @Query("SELECT * FROM todos WHERE taskDate = :date ORDER BY taskDate ASC")
    fun getTasksByDate(date: String): Flow<List<Task>>
}