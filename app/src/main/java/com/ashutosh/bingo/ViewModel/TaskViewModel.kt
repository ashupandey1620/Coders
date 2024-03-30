package com.ashutosh.bingo.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ashutosh.bingo.R
import com.ashutosh.bingo.Repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {

    var appState by mutableStateOf(MainState())


}


data class MainState(
    val buildVersion: String = "0.0" ,
    val theme: AppTheme = AppTheme.Dark ,
    val sortBy: SortTask = SortTask.BY_START_TIME_ASCENDING ,
    val freeTime: Long? = null ,
    var totalTaskDuration: Long = 0 ,
    val durationList: List<Long> = listOf(30, 60, 90, 0) ,
    val streak: Int = -1 ,
    val sleepTime: LocalTime = LocalTime.MAX ,
)

enum class AppTheme {
    Light, Dark, Amoled;
}

enum class SortTask(val stringId: Int) {
    BY_PRIORITY_ASCENDING(R.string.priority_low_to_high),
    BY_PRIORITY_DESCENDING(R.string.priority_high_to_low),
    BY_START_TIME_ASCENDING(R.string.start_time_latest_at_bottom),
    BY_START_TIME_DESCENDING(R.string.start_time_latest_at_top),
    BY_CREATE_TIME_ASCENDING(R.string.creation_time_latest_at_bottom),
    BY_CREATE_TIME_DESCENDING(R.string.creation_time_latest_at_top),
}