package com.ashutosh.bingo.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ashutosh.bingo.ViewModel.TaskViewModel
import com.ashutosh.bingo.screens.Add_Edit_Screen.AddTaskScreen
import com.ashutosh.bingo.screens.Add_Edit_Screen.EditTaskScreen
import com.ashutosh.bingo.screens.Calendar.CalenderScreen
import com.ashutosh.bingo.screens.CompletedTask.CompletedTaskScreen
import com.ashutosh.bingo.screens.FreeTime.FreeTimeScreen
import com.ashutosh.bingo.screens.HomeScreen.HomeScreen
import com.ashutosh.bingo.screens.Pomodoro.PomodoroScreen
import com.ashutosh.bingo.screens.PresentWeekTask.ThisWeekTaskScreen
import java.time.LocalDate

@Composable
fun AppNavigation(taskViewModel: TaskViewModel) {
    val navController = rememberNavController()
    val todayTasks by taskViewModel.todayTaskList.collectAsStateWithLifecycle(initialValue = emptyList())
    val allTasks by taskViewModel.taskList.collectAsStateWithLifecycle(initialValue = emptyList())

    val dayOfWeek = LocalDate.now().dayOfWeek.value - 1
    val updatedTodayTasks = todayTasks.filter { task ->
        if (task.isRepeated) {
            task.getRepeatWeekList().contains(dayOfWeek)
        } else {
            true
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen.name
    ) {
        composable(route = Routes.HomeScreen.name) {
            HomeScreen(
                tasks = updatedTodayTasks,
                appState = taskViewModel.appState,
                onMainEvent = taskViewModel::onEvent,
                onEvent = taskViewModel::onEvent,
                onEditTask = { id ->
                    navController.navigate(route = "${Routes.EditTaskScreen.name}/$id")
                },
                onAddTask = {
                    navController.navigate(route = Routes.AddTaskScreen.name)
                },
                onClickCompletedInfo = {
                    navController.navigate(route = Routes.CompletedTaskScreen.name)
                },
                onClickCalender = {
                    navController.navigate(route = Routes.CalenderScreen.name)
                },
                onClickThisWeek = {
                    navController.navigate(route = Routes.ThisWeekTaskScreen.name)
                },
                onClickFreeTimeInfo = {
                    navController.navigate(route = Routes.FreeTimeScreen.name)
                },
                onPomodoroTask = { id ->
                    navController.navigate(route = "${Routes.PomodoroScreen.name}/$id")
                })
        }

        composable(route = Routes.CompletedTaskScreen.name) {
            CompletedTaskScreen(
                tasks = updatedTodayTasks,
                onEvent = taskViewModel::onEvent,
                onBack = {
                    if (navController.isValidBackStack) {
                        navController.popBackStack()
                    }
                })
        }

        composable(route = Routes.ThisWeekTaskScreen.name) {
            ThisWeekTaskScreen(
                tasks = allTasks,
                onEditTask = { id ->
                    navController.navigate(route = "${Routes.EditTaskScreen.name}/$id")
                },
                onEvent = taskViewModel::onEvent,
                onBack = {
                    if (navController.isValidBackStack) {
                        navController.popBackStack()
                    }
                })
        }

        composable(route = Routes.CalenderScreen.name) {
            CalenderScreen(
                tasks = allTasks,
                onEditTask = { id ->
                    navController.navigate(route = "${Routes.EditTaskScreen.name}/$id")
                },
                onEvent = taskViewModel::onEvent,
                onPomodoroTask = { id ->
                    navController.navigate(route = "${Routes.PomodoroScreen.name}/$id")
                },
                onBack = {
                    if (navController.isValidBackStack) {
                        navController.popBackStack()
                    }
                })
        }

        composable(route = Routes.FreeTimeScreen.name) {
            FreeTimeScreen(
                tasks = updatedTodayTasks,
                onBack = {
                    if (navController.isValidBackStack) {
                        navController.popBackStack()
                    }
                })
        }

        composable(route = Routes.AddTaskScreen.name) {
            AddTaskScreen(
                appState = taskViewModel.appState,
                onEvent = taskViewModel::onEvent,
                onBack = {
                    if (navController.isValidBackStack) {
                        navController.popBackStack()
                    }
                })
        }

        composable(
            route = "${Routes.EditTaskScreen.name}/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
                defaultValue = -1
            })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt("id").let { id ->
                EditTaskScreen(task = taskViewModel.task,
                    appState = taskViewModel.appState,
                    onEvent = taskViewModel::onEvent,
                    onBack = {
                        if (navController.isValidBackStack) {
                            navController.popBackStack()
                        }
                    })
            }
        }

        composable(
            route = "${Routes.PomodoroScreen.name}/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
                defaultValue = -1
            })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt("id").let { id ->
                PomodoroScreen(task = taskViewModel.task,
                    onEvent = taskViewModel::onEvent,
                    onBack = {
                        if (navController.isValidBackStack) {
                            navController.popBackStack()
                        }
                    })
            }
        }
    }
}

val NavHostController.isValidBackStack
    get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED



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