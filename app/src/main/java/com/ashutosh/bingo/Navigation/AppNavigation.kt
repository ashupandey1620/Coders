package com.ashutosh.bingo.Navigation

import androidx.compose.runtime.Composable
import com.ashutosh.bingo.ViewModel.TaskViewModel

@Composable
fun AppNavigation(taskViewModel: TaskViewModel) {



}


enum class Routes {
    HomeScreen,
    AddTaskScreen,
    EditTaskScreen,
    CompletedTaskScreen,
    PomodoroScreen,
    FreeTimeScreen,
    ThisWeekTaskScreen,
    CalenderScreen,
    SettingsScreen,
}