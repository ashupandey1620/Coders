package com.ashutosh.bingo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ashutosh.bingo.domain.models.Task

@Database(
    entities = [Task::class],
    version = 2,
)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}