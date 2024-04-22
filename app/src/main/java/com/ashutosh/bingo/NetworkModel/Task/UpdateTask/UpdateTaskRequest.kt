package com.ashutosh.bingo.NetworkModel.Task.UpdateTask

data class UpdateTaskRequest(
    val description: String,
    val startTime: String,
    val taskId: String,
    val taskName: String
)