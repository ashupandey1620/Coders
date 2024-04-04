package com.ashutosh.bingo.screens.PresentWeekTask

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Today
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ashutosh.bingo.Components.h1TextStyle
import com.ashutosh.bingo.Components.infoTextStyle
import com.ashutosh.bingo.domain.models.Task
import com.ashutosh.bingo.screens.HomeScreen.Components.EmptyTaskComponent
import com.ashutosh.bingo.screens.HomeScreen.Components.TaskComponent
import com.ashutosh.bingo.screens.HomeScreen.HomeScreenEvent
import com.ashutosh.bingo.ui.theme.BingoTheme
import com.ashutosh.bingo.util.Constants
import com.ashutosh.bingo.util.DummyTasks
import java.time.LocalDate

@OptIn(
	ExperimentalMaterial3Api::class,
	ExperimentalFoundationApi::class
)
@Composable
fun ThisWeekTaskScreen(
	tasks: List<Task> ,
	onEvent: (HomeScreenEvent) -> Unit ,
	onEditTask: (id: Int) -> Unit ,
	onBack: () -> Unit
) {

	val repeatedTasks = tasks.filter { it.isRepeated }

	Scaffold(topBar = {
		TopAppBar(
			colors = TopAppBarDefaults.topAppBarColors(
				containerColor = MaterialTheme.colorScheme.background,
			),
			title = {
				Text(
					text = "This Week",
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
			actions = {
				Row(
					verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.spacedBy(4.dp),
					modifier = Modifier.padding(end = 16.dp)
				) {
					Icon(
						imageVector = Icons.Default.Today, contentDescription = null,
						tint = MaterialTheme.colorScheme.onPrimary
					)
					Text(
						text = LocalDate.now().dayOfWeek.name.take(3),
						style = infoTextStyle,
						color = MaterialTheme.colorScheme.onPrimary
					)
				}
			}
		)
	}) { innerPadding ->

		Column(modifier = Modifier.padding(innerPadding)) {

			if (repeatedTasks.isEmpty()) {
				EmptyTaskComponent()
			} else {
				LazyColumn(
					modifier = Modifier
						.fillMaxSize()
						.padding(
							16.dp,
							0.dp
						)
				) {
					itemsIndexed(items = repeatedTasks,
						key = { index, task ->
							task.id
						}) { index, task ->
						Box(
							modifier = Modifier.animateItemPlacement(tween(500))
						) {
							TaskComponent(
								task = task,
								onEdit = {
									onEvent(HomeScreenEvent.OnEditTask(it))
									onEditTask(it)
								},
								onComplete = {},
								onPomodoro = {},
								animDelay = index * Constants.LIST_ANIMATION_DELAY
							)
						}
						Spacer(modifier = Modifier.height(10.dp))
					}
				}
			}
		}
	}
}

@Preview
@Composable
fun ThisWeekTaskScreenPreview() {
	BingoTheme {
		val tasks = DummyTasks.dummyTasks
		ThisWeekTaskScreen(
			tasks = tasks,
			{}, {}, {}
		)
	}
}