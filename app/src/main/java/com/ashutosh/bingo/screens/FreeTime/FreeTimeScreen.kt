package com.ashutosh.bingo.screens.FreeTime

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ashutosh.bingo.Components.h1TextStyle
import com.ashutosh.bingo.Components.h2TextStyle
import com.ashutosh.bingo.Components.taskTextStyle
import com.ashutosh.bingo.R
import com.ashutosh.bingo.domain.models.Task
import com.ashutosh.bingo.screens.FreeTime.Components.CustomPieChart
import com.ashutosh.bingo.screens.FreeTime.Components.PieChartItemComponent
import com.ashutosh.bingo.ui.theme.BingoTheme
import com.ashutosh.bingo.ui.theme.pieChartColors
import com.ashutosh.bingo.util.Constants
import com.ashutosh.bingo.util.DummyTasks
import com.ashutosh.bingo.util.getFreeTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FreeTimeScreen(
	tasks: List<Task> ,
	onBack: () -> Unit
) {

	val inCompletedTasks = tasks.filter { !it.isCompleted }
	val sortedTasks = inCompletedTasks.sortedBy { -it.getDuration() }
	val totalColors = pieChartColors.size

	// todo :save free time in app state
	val totalTaskTime = inCompletedTasks.sumOf { it.getDuration(checkPastTask = true) }
	val freeTimeText = getFreeTime(totalTaskTime)

	Scaffold(topBar = {
		TopAppBar(
			colors = TopAppBarDefaults.topAppBarColors(
				containerColor = MaterialTheme.colorScheme.background,
			),
			title = {
				Text(
					text = "Analysis",
					style = h1TextStyle
				)
			},
			navigationIcon = {
				IconButton(onClick = { onBack() }) {
					Icon(
						imageVector = Icons.Rounded.ArrowBack,
						contentDescription = null
					)
				}
			},
		)
	}) { innerPadding ->

		Column(modifier = Modifier.padding(innerPadding)) {

			val pieChartBgGradient = Brush.verticalGradient(
				listOf(
					MaterialTheme.colorScheme.primary,
					MaterialTheme.colorScheme.secondary,
				)
			)
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.background(
						pieChartBgGradient,
						RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
					)
					.padding(bottom = 24.dp),
				contentAlignment = Alignment.Center
			) {
				val data = sortedTasks.map { it.getDuration() }
				CustomPieChart(data = data, pieChartSize = 180.dp)

				Column(horizontalAlignment = Alignment.CenterHorizontally) {
					Text(
						text = "Free Time",
						color = MaterialTheme.colorScheme.onPrimary,
						style = h2TextStyle
					)

					Text(
						text = freeTimeText,
						color = MaterialTheme.colorScheme.onPrimary,
						style = taskTextStyle
					)
				}
			}

			Spacer(modifier = Modifier.height(16.dp))

			LazyColumn(
				modifier = Modifier
					.fillMaxSize()
					.padding(horizontal = 16.dp)
			) {
				itemsIndexed(items = sortedTasks) { index, item ->
					PieChartItemComponent(
						task = item,
						itemColor = pieChartColors[index % totalColors],
						animDelay = index * Constants.LIST_ANIMATION_DELAY
					)
					Spacer(modifier = Modifier.height(10.dp))
				}
			}
		}
	}
}

@Preview
@Composable
fun FreeTimeScreenPreview() {
	val tasks = DummyTasks.dummyTasks
	BingoTheme {
		FreeTimeScreen(tasks = tasks, {})
	}
}