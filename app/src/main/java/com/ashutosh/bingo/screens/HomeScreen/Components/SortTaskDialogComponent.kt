package com.ashutosh.bingo.screens.HomeScreen.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ashutosh.bingo.Components.h2TextStyle
import com.ashutosh.bingo.Components.h3TextStyle
import com.ashutosh.bingo.Components.taskTextStyle
import com.ashutosh.bingo.R
import com.ashutosh.bingo.ViewModel.SortTask
import com.ashutosh.bingo.ui.theme.BingoTheme


@Composable
fun SortTaskDialogComponent(
	defaultSortTask: SortTask ,
	onClose: () -> Unit ,
	onSelect: (SortTask) -> Unit
) {

	var selectedOption by remember {
		mutableStateOf(defaultSortTask)
	}

	Dialog(onDismissRequest = { onClose() }) {
		Card(
			modifier = Modifier
				.fillMaxWidth(1f),
			colors = CardDefaults.cardColors(
				containerColor = MaterialTheme.colorScheme.secondary,
				contentColor = MaterialTheme.colorScheme.onPrimary
			)
		) {
			Text(
				modifier = Modifier.padding(
					start = 16.dp,
					top = 24.dp,
					bottom = 16.dp
				),
				text = "Sort Tasks By",
				style = h2TextStyle
			)

			SortTask.entries.forEach {
				CustomRadioButton(
					label = stringResource(id = it.stringId),
					isSelected = selectedOption == it
				) {
					selectedOption = it
				}
			}

			Text(
				modifier = Modifier
					.padding(
						bottom = 24.dp,
						end = 32.dp
					)
					.clickable { onSelect(selectedOption) }
					.align(Alignment.End),
				text = "Select",
				style = h3TextStyle,
				color = Blue
			)
		}
	}
}

@Composable
fun CustomRadioButton(label: String, isSelected: Boolean, onClick: () -> Unit) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.fillMaxWidth()
			.padding(bottom = 8.dp)
			.clickable { onClick() }) {
		RadioButton(
			colors = RadioButtonDefaults.colors(selectedColor = Blue),
			selected = isSelected,
			onClick = { onClick() })
		Text(
			text = label,
			style = taskTextStyle,
			color = MaterialTheme.colorScheme.onPrimary
		)
	}
}

@Preview
@Composable
fun SortTaskDialogComponentPreview() {
	BingoTheme {
		SortTaskDialogComponent(
			SortTask.BY_CREATE_TIME_ASCENDING,
			{},
			{})
	}
}