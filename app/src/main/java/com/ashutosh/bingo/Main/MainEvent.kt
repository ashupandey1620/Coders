package com.ashutosh.bingo.Main

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.ashutosh.bingo.CommonScreens.AppTheme
import com.ashutosh.bingo.ViewModel.SortTask


sealed class MainEvent {
	data class UpdateAppTheme(val theme: AppTheme , val context: Context) : MainEvent()
	data class UpdateSortByTask(val sortTask: SortTask , val context: Context) : MainEvent()
	data class UpdateFreeTime(val freeTime: Long) : MainEvent()
	data class OnClickNavDrawerItem(val context: Context, val item: NavDrawerItem) : MainEvent()
}


enum class NavDrawerItem(val stringId: String , val icon: ImageVector) {
	REPORT_BUGS("Report Bugs", Icons.Default.BugReport),
	SUGGESTIONS("Suggestions", Icons.Default.Chat),
	RATE_US("Rate Us", Icons.Default.Star),
	SHARE_APP("Share App", Icons.Default.Share)
}