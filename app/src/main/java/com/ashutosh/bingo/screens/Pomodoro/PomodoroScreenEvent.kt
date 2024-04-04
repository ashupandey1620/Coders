package com.ashutosh.bingo.screens.Pomodoro

sealed class PomodoroScreenEvent {
	data class OnCompleted(val taskId: Int, val isCompleted: Boolean) : PomodoroScreenEvent()
	data class OnDestroyScreen(val taskId: Int, val remainingTime: Long) : PomodoroScreenEvent()
}