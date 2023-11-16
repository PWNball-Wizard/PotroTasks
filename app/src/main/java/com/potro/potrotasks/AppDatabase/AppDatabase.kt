package com.potro.potrotasks.AppDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.potro.potrotasks.domain.model.Task



@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(DateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        const val NAME = "todos"
    }
}