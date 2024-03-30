package com.ashutosh.bingo.ViewModel

import androidx.lifecycle.ViewModel
import com.ashutosh.bingo.Repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {




}