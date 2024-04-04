package com.ashutosh.bingo.screens.CompletedTask

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ashutosh.bingo.Components.h1TextStyle
import com.ashutosh.bingo.domain.models.Task
import com.ashutosh.bingo.screens.HomeScreen.Components.EmptyTaskComponent
import com.ashutosh.bingo.screens.HomeScreen.Components.TaskComponent
import com.ashutosh.bingo.screens.HomeScreen.HomeScreenEvent
import com.ashutosh.bingo.ui.theme.BingoTheme
import com.ashutosh.bingo.util.Constants
import com.ashutosh.bingo.util.DummyTasks

@OptIn(
	ExperimentalMaterial3Api::class,
	ExperimentalFoundationApi::class
)
@Composable
fun CompletedTaskScreen(
	tasks: List<Task> ,
	onEvent: (HomeScreenEvent) -> Unit ,
	onBack: () -> Unit
) {

	val completedTasks = mutableListOf<Task>()
	tasks.filterTo(completedTasks) { it.isCompleted }

	Scaffold(topBar = {
		TopAppBar(
			colors = TopAppBarDefaults.topAppBarColors(
				containerColor = MaterialTheme.colorScheme.background,
			),
			title = {
				Text(
					text = "Completed Tasks",
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

			if (completedTasks.isEmpty()) {
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
					itemsIndexed(items = completedTasks,
						key = { index, task ->
							task.id
						}) { index, task ->
						Box(
							modifier = Modifier.animateItemPlacement(tween(500))
						) {
							TaskComponent(
								task = task,
								onEdit = {},
								onComplete = {
									onEvent(
										HomeScreenEvent.OnCompleted(
											it,
											false
										)
									)
								},
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
fun CompletedTaskScreenPreview() {
	BingoTheme {
		val tasks = DummyTasks.dummyTasks
		CompletedTaskScreen(
			tasks = tasks,
			{},
			{}
		)
	}
}