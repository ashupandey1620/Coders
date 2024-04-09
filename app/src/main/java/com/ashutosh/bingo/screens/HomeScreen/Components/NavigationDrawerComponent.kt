package com.ashutosh.bingo.screens.HomeScreen.Components

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarViewWeek
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Deck
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
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
import com.ashutosh.bingo.Components.taskDescTextStyle
import com.ashutosh.bingo.Components.taskTextStyle
import com.ashutosh.bingo.Main.MainEvent
import com.ashutosh.bingo.R
import com.ashutosh.bingo.ViewModel.MainState
import com.ashutosh.bingo.ui.theme.Pink80


@Composable
fun NavigationDrawerComponent(
	appState: MainState ,
	onMainEvent: (MainEvent) -> Unit ,
	onClickThisWeek: () -> Unit
) {

	val context = LocalContext.current

	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(8.dp),
		modifier = Modifier
			.fillMaxHeight()
			.fillMaxWidth(0.7f)
	) {

		Spacer(modifier = Modifier.height(25.dp))
		Column(
			horizontalAlignment = Alignment.CenterHorizontally
		) {

			Card(modifier = Modifier.wrapContentSize(),
				shape = RoundedCornerShape(15.dp),
				border = BorderStroke(2.dp, Pink80)
			){
				Image(
					painter = painterResource(if (appState.theme == AppTheme.Amoled) R.drawable.logobingo else R.drawable.logobingo) ,
					contentDescription = null ,
					modifier = Modifier
						.width(150.dp)
						.wrapContentHeight() ,
				)
			}

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

		Divider(thickness = 2.dp, color = MaterialTheme.colorScheme.secondary)

		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.spacedBy(16.dp)
		) {
			Text(
				text = "Theme",
				style = h3TextStyle,
				color = MaterialTheme.colorScheme.onPrimary
			)
//			if (appState.streak != -1)
				ThemeOptionComponent(defaultTheme = appState.theme) {
					onMainEvent(MainEvent.UpdateAppTheme(it, context))
				}
		}



		Divider(thickness = 2.dp, color = MaterialTheme.colorScheme.secondary)

		Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
			NavDrawerItemUI(
				icon = Icons.Default.Person,
				label = "Your Profile"
			) {}

			NavDrawerItemUI(
				icon = Icons.Default.CalendarViewWeek,
				label = "This Week"
			) { onClickThisWeek() }

			NavDrawerItemUI(
				icon = Icons.Default.CalendarMonth,
				label = "This Month"
			) {}

			NavDrawerItemUI(
				icon = Icons.Default.Code,
				label = "CP"
			) {}

			NavDrawerItemUI(
				icon = Icons.Default.Coffee,
				label = "Developers"
			) {}

			NavDrawerItemUI(
				icon = Icons.Default.Deck,
				label = "App Info"
			) {}


//			NavDrawerItem.entries.forEach {
//				NavDrawerItemUI(icon = it.icon, label = it.stringId) {
//					onMainEvent(MainEvent.OnClickNavDrawerItem(context, it))
//				}
//			}
		}

	}

}

@Composable
fun NavDrawerItemUI(icon: ImageVector, label: String, onClick: () -> Unit) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.clickable { onClick() }
			.padding(20.dp , 8.dp),
		horizontalArrangement = Arrangement.spacedBy(5.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Icon(
			modifier = Modifier.size(25.dp),
			imageVector = icon,
			contentDescription = null,
			tint = MaterialTheme.colorScheme.onPrimary
		)
		Text(text = label, style = h3TextStyle, color = MaterialTheme.colorScheme.onPrimary)
	}
}

@Preview(widthDp = 350)
@Composable
fun NavigationDrawerComponentPreview() {
	NavigationDrawerComponent(MainState(), {}, {})
}