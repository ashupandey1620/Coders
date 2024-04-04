package com.ashutosh.bingo.Main

import android.content.Context
import com.ashutosh.bingo.ViewModel.AppTheme
import com.ashutosh.bingo.ViewModel.SortTask


sealed class MainEvent {
	data class UpdateAppTheme(val theme: AppTheme , val context: Context) : MainEvent()
	data class UpdateSortByTask(val sortTask: SortTask , val context: Context) : MainEvent()
	data class UpdateFreeTime(val freeTime: Long) : MainEvent()
	data class OnClickNavDrawerItem(val context: Context, val item: NavDrawerItem) : MainEvent()
}