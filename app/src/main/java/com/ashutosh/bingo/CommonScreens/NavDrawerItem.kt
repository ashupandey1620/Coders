package com.ashutosh.bingo.CommonScreens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector


enum class NavDrawerItem(val stringId: String , val icon: ImageVector) {
	REPORT_BUGS("Report Bugs", Icons.Default.BugReport),
	SUGGESTIONS("Suggestions", Icons.Default.Chat),
	RATE_US("Rate Us", Icons.Default.Star),
	SHARE_APP("Share App", Icons.Default.Share)
}