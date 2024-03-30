package com.ashutosh.bingo.domain.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

data class Task @RequiresApi(Build.VERSION_CODES.O) constructor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0 ,
    val uuid: String ,
    val title: String = "" ,
    val isCompleted: Boolean = false ,
    val startTime: LocalTime = LocalTime.now() ,
    val endTime: LocalTime = LocalTime.now() ,
    val reminder: Boolean = false ,
    val isRepeated: Boolean = false ,
    val repeatWeekdays: String = "" ,
    val pomodoroTimer: Int = -1 ,
    val date: LocalDate = LocalDate.now() ,
    val priority: Int = 0 ,
) {


}