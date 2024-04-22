package com.ashutosh.bingo.NetworkModel.Task.AddTask

data class AddTaskRequest(
    val description: String,
    val endTime: String,
    val startTime: String,
    val taskName: String
)