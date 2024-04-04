package com.ashutosh.bingo.Repository

import com.ashutosh.bingo.data.TaskDao
import com.ashutosh.bingo.domain.models.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class TaskRepository(private val dao: TaskDao) {

    suspend fun insertTask(task: Task) {
        dao.insertTask(task)
    }

    suspend fun deleteTask(task: Task) {
        dao.deleteTask(task)
    }

    suspend fun updateTask(task: Task) {
        dao.updateTask(task)
    }

    suspend fun getTaskById(id: Int): Task {
        return dao.getTaskById(id)
    }

    suspend fun deleteAllTasks() {
        dao.deleteAllTasks()
    }

    fun getTasksByDate(selectedDate: LocalDate): Flow<List<Task>> {
        return dao.getTasksByDate(selectedDate.toString())
    }

    fun getTodayTasks(): Flow<List<Task>> {
        return dao.getTasksByDate(LocalDate.now().toString())
    }

    fun getLastRepeatedTasks(): List<Task> {
        val today = LocalDate.now().toString()
        return dao.getLastRepeatedTasks(today)
    }

    fun getAllTasks(): Flow<List<Task>> {
        return dao.getAllTasks()
    }

}