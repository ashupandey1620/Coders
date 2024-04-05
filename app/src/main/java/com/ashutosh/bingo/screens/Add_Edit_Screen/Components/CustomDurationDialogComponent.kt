package com.ashutosh.bingo.screens.Add_Edit_Screen.Components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ashutosh.bingo.Components.durationTextStyle
import com.ashutosh.bingo.Components.h3TextStyle
import com.ashutosh.bingo.ui.theme.BingoTheme
import com.commandiron.wheel_picker_compose.WheelTimePicker
import java.time.LocalTime

@Composable
fun CustomDurationDialogComponent(
	duration: Int = 120,
	onClose: () -> Unit,
	onSelect: (LocalTime) -> Unit
) {
	Dialog(onDismissRequest = { onClose() }) {


		val hours = duration / 60
		val minutes = duration % 60

		var customDuration = LocalTime.of(hours, minutes)

		Card(
			modifier = Modifier
				.fillMaxWidth()
				.border(4.dp, MaterialTheme.colorScheme.secondary, RoundedCornerShape(16.dp)),
			shape = RoundedCornerShape(16.dp),
			colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
		) {
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.padding(
						32.dp,
						16.dp
					),
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.spacedBy(24.dp)
			) {
				Text(
					text = "Custom Duration",
					color = MaterialTheme.colorScheme.onPrimary,
					style = durationTextStyle
				)
				WheelTimePicker(
					startTime = LocalTime.of(hours, minutes),
					textColor = MaterialTheme.colorScheme.onPrimary,
					onSnappedTime = { customDuration = it }
				)
				Text(
					modifier = Modifier
						.padding(8.dp)
						.clickable {
							onSelect(customDuration)
							onClose()
						}
						.align(Alignment.End),
					text = "Done",
					style = h3TextStyle,
					color = Blue
				)
			}
		}
	}
}

@Preview
@Composable
fun CustomDurationDialogComponentPreview() {
	BingoTheme {
		CustomDurationDialogComponent(onClose = {}, onSelect = {})
	}
}