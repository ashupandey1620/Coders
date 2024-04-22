package com.ashutosh.bingo.NetworkModel.Task.AddTask

data class AddTaskResponse(
    val `data`: Data,
    val message: String,
    val statusCode: Int,
    val success: Boolean
)
data class Data(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val description: String,
    val endTime: String,
    val startTime: String,
    val success: Boolean,
    val taskName: String,
    val updatedAt: String,
    val userName: String
)