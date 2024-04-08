package com.ashutosh.bingo.screens.HomeScreen.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashutosh.bingo.CommonScreens.AppTheme
import com.ashutosh.bingo.Components.fontRobotoMono
import com.ashutosh.bingo.Components.h2TextStyle
import com.ashutosh.bingo.Components.h3TextStyle
import com.ashutosh.bingo.Components.taskTextStyle
import com.ashutosh.bingo.Main.MainEvent
import com.ashutosh.bingo.Main.NavDrawerItem
import com.ashutosh.bingo.R
import com.ashutosh.bingo.ViewModel.MainState


@Composable
fun NavigationDrawerComponent(
	appState: MainState ,
	onMainEvent: (MainEvent) -> Unit ,
	onClickThisWeek: () -> Unit
) {

	val context = LocalContext.current

	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(16.dp),
		modifier = Modifier
			.fillMaxHeight()
			.fillMaxWidth(0.7f)
			.padding(vertical = 64.dp)
	) {

		Spacer(modifier = Modifier.height(50.dp))
		Column(
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Image(
				painter = painterResource(if (appState.theme == AppTheme.Amoled) R.drawable.app_logo_amoled else R.drawable.app_logo),
				contentDescription = null,
				modifier = Modifier.size(64.dp),
			)
			Spacer(modifier = Modifier.height(16.dp))
			Text(
				text = "Bingo",
				style = h2TextStyle,
				color = MaterialTheme.colorScheme.onPrimary
			)
			Text(
				text = "Bingo v1.0.0",
				fontFamily = fontRobotoMono,
				fontSize = 15.sp,
				fontWeight = FontWeight.Bold,
				color = MaterialTheme.colorScheme.onPrimary
			)
		}
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.spacedBy(16.dp)
		) {
			Text(
				text = "App theme",
				style = h3TextStyle,
				color = MaterialTheme.colorScheme.onPrimary
			)
			if (appState.streak != -1)
				ThemeOptionComponent(defaultTheme = appState.theme) {
					onMainEvent(MainEvent.UpdateAppTheme(it, context))
				}
		}



		Divider(thickness = 2.dp, color = MaterialTheme.colorScheme.secondary)

		Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
			NavDrawerItemUI(
				icon = Icons.Default.CalendarMonth,
				label = "This Week"
			) { onClickThisWeek() }

			NavDrawerItem.entries.forEach {
				NavDrawerItemUI(icon = it.icon, label = it.stringId) {
					onMainEvent(MainEvent.OnClickNavDrawerItem(context, it))
				}
			}
		}

	}

}

@Composable
fun NavDrawerItemUI(icon: ImageVector, label: String, onClick: () -> Unit) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.clickable { onClick() }
			.padding(32.dp, 8.dp),
		horizontalArrangement = Arrangement.spacedBy(10.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Icon(
			modifier = Modifier.size(32.dp),
			imageVector = icon,
			contentDescription = null,
			tint = MaterialTheme.colorScheme.onPrimary
		)
		Text(text = label, style = taskTextStyle, color = MaterialTheme.colorScheme.onPrimary)
	}
}

@Preview(widthDp = 350)
@Composable
fun NavigationDrawerComponentPreview() {
	NavigationDrawerComponent(MainState(), {}, {})
}