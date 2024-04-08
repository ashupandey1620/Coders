package com.ashutosh.bingo.screens.HomeScreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ashutosh.bingo.CommonScreens.SwipeActionBox
import com.ashutosh.bingo.Components.SnackbarController.showCustomSnackbar
import com.ashutosh.bingo.Components.durationTextStyle
import com.ashutosh.bingo.Components.fontRobotoMono
import com.ashutosh.bingo.Components.h1TextStyle
import com.ashutosh.bingo.Components.h2TextStyle
import com.ashutosh.bingo.Main.MainEvent
import com.ashutosh.bingo.R
import com.ashutosh.bingo.ViewModel.MainState
import com.ashutosh.bingo.ViewModel.SortTask
import com.ashutosh.bingo.domain.models.Task
import com.ashutosh.bingo.screens.HomeScreen.Components.EmptyTaskComponent
import com.ashutosh.bingo.screens.HomeScreen.Components.InfoComponent
import com.ashutosh.bingo.screens.HomeScreen.Components.NavigationDrawerComponent
import com.ashutosh.bingo.screens.HomeScreen.Components.SortTaskDialogComponent
import com.ashutosh.bingo.screens.HomeScreen.Components.TaskComponent
import com.ashutosh.bingo.ui.theme.BingoTheme
import com.ashutosh.bingo.util.Constants
import com.ashutosh.bingo.util.DummyTasks
import com.ashutosh.bingo.util.getFreeTime
import kotlinx.coroutines.launch


@OptIn(
	ExperimentalMaterial3Api::class,
	ExperimentalFoundationApi::class
)
@Composable
fun HomeScreen(
	tasks: List<Task> ,
	appState: MainState ,
	onMainEvent: (MainEvent) -> Unit ,
	onEvent: (HomeScreenEvent) -> Unit ,
	onEditTask: (id: Int) -> Unit ,
	onAddTask: () -> Unit ,
	onClickCompletedInfo: () -> Unit ,
	onClickCalender: () -> Unit ,
	onClickThisWeek: () -> Unit ,
	onClickFreeTimeInfo: () -> Unit ,
	onPomodoroTask: (id: Int) -> Unit ,
) {
	val completedTasks = tasks.filter { it.isCompleted }
	val inCompletedTasks = tasks.filter { !it.isCompleted }

	// calc free time
	val totalTaskTime = inCompletedTasks.sumOf { it.getDuration(checkPastTask = true) }
	val freeTimeText = getFreeTime(totalTaskTime)

	// streak
	val appStreakText = if (appState.streak != -1) appState.streak.toString() else "0"

	LaunchedEffect(inCompletedTasks) {
		appState.totalTaskDuration = totalTaskTime
	}

	val totalTasks = tasks.size
	val totalCompletedTasks = completedTasks.size
	val context = LocalContext.current

	// animation
	val translateX = 600f
	val leftTranslate = remember { Animatable(-translateX) }
	val rightTranslate = remember { Animatable(translateX) }

	LaunchedEffect(key1 = Unit) {
		launch {
			leftTranslate.animateTo(
				0f,
				tween(1000)
			)
		}
		launch {
			rightTranslate.animateTo(
				0f,
				tween(1000)
			)
		}
	}

	//sort dialog
	var showSortDialog by remember {
		mutableStateOf(false)
	}

	// navigation drawer
	val scope = rememberCoroutineScope()
	val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

	ModalNavigationDrawer(
		drawerState = drawerState,
		drawerContent = {
			ModalDrawerSheet(drawerContainerColor = MaterialTheme.colorScheme.primary) {
				NavigationDrawerComponent(
					appState,
					onMainEvent,
					onClickThisWeek = {
						onClickThisWeek.invoke()
						scope.launch {
							drawerState.close()
						}
					}
				)
			}
		}) {
		Scaffold(topBar = {
			TopAppBar(
				colors = TopAppBarDefaults.topAppBarColors(
					containerColor = Color.Transparent
				),
				title = {
					Text(
						text = "Bingo",
						style = h1TextStyle
					)
				},
				navigationIcon = {
					IconButton(onClick = {
						scope.launch {
							drawerState.apply {
								if (isClosed) open() else close()
							}
						}
					}) {
						Icon(
							imageVector = Icons.Default.Menu,
							contentDescription = null
						)
					}
				},
				actions = {
					Row(
						horizontalArrangement = Arrangement.spacedBy(2.dp),
						verticalAlignment = Alignment.CenterVertically
					) {
						IconButton(onClick = { onClickCalender() }) {
							Icon(
								imageVector = Icons.Default.CalendarMonth,
								contentDescription = null
							)
						}
						Text(
							text = appStreakText,
							style = durationTextStyle,
							color = MaterialTheme.colorScheme.onSecondary,
							fontFamily = fontRobotoMono,
							fontWeight = FontWeight.Bold
						)
						Icon(
							painter = painterResource(id = R.drawable.ic_fire),
							contentDescription = null,
							tint = Yellow,
						)
					}
					Spacer(modifier = Modifier.width(8.dp))
				})
		},
			floatingActionButton = {
				FloatingActionButton(
					onClick = {
						onAddTask()
					},
					containerColor = Blue,
					contentColor = MaterialTheme.colorScheme.secondary
				) {
					Icon(
						imageVector = Icons.Default.Add,
						contentDescription = null
					)
				}
			}) { innerPadding ->

			// sort dialog
			if (showSortDialog)
				SortTaskDialogComponent(
					defaultSortTask = appState.sortBy,
					onClose = { showSortDialog = false },
					onSelect = {
						onMainEvent(MainEvent.UpdateSortByTask(it, context = context))
						showSortDialog = false
					}
				)

			Column(modifier = Modifier.padding(innerPadding)) {
				Row(
					modifier = Modifier
						.fillMaxWidth()
						.padding(
							16.dp,
							8.dp
						),
					horizontalArrangement = Arrangement.spacedBy(16.dp),
					verticalAlignment = Alignment.CenterVertically
				) {

					InfoComponent(
						title = "Completed",
						desc = "$totalCompletedTasks/$totalTasks Tasks",
						icon = R.drawable.ic_task_list,
						backgroundColor = Green,//light green
						modifier = Modifier
							.weight(1f)
							.graphicsLayer {
								translationX = leftTranslate.value
							},
						onClick = { onClickCompletedInfo() }
					)

					InfoComponent(
						title = "Free Time",
						desc = freeTimeText,
						icon = R.drawable.ic_clock,
						backgroundColor = Blue,
						modifier = Modifier
							.weight(1f)
							.graphicsLayer {
								translationX = rightTranslate.value
							},
						onClick = {
							if (inCompletedTasks.isEmpty()) {
								showCustomSnackbar("Add Tasks to Analyze")
							} else {
								onClickFreeTimeInfo()
							}
						}
					)

				}

				if (inCompletedTasks.isEmpty()) {
					EmptyTaskComponent()
				} else {

					Row(
						modifier = Modifier
							.fillMaxWidth()
							.padding(horizontal = 8.dp),
						horizontalArrangement = Arrangement.SpaceBetween,
						verticalAlignment = Alignment.CenterVertically
					) {

						Text(
							text = "Today Task",
							style = h2TextStyle,
							color = MaterialTheme.colorScheme.onPrimary,
							modifier = Modifier.padding(16.dp)
						)

						IconButton(onClick = { showSortDialog = true }) {
							Icon(
								imageVector = Icons.Default.Sort,
								contentDescription = null,
								tint = MaterialTheme.colorScheme.onPrimary
							)
						}

					}

					// sort task list
					val sortedTasks: List<Task> = remember(inCompletedTasks, appState.sortBy) {
						inCompletedTasks.sortedWith(compareBy {
							when (appState.sortBy) {
								SortTask.BY_CREATE_TIME_ASCENDING -> {
									it.id
								}

								SortTask.BY_CREATE_TIME_DESCENDING -> {
									-it.id
								}

								SortTask.BY_PRIORITY_ASCENDING -> {
									it.priority
								}

								SortTask.BY_PRIORITY_DESCENDING -> {
									-it.priority
								}

								SortTask.BY_START_TIME_ASCENDING -> {
									it.startTime.toSecondOfDay()
								}

								SortTask.BY_START_TIME_DESCENDING -> {
									-it.startTime.toSecondOfDay()
								}
							}
						})
					}

					LazyColumn(
						modifier = Modifier
							.fillMaxSize()
							.padding(
								16.dp,
								0.dp
							)
					) {

						itemsIndexed(items = sortedTasks,
							key = { _, task ->
								task.id
							}) { index, task ->
							Box(
								modifier = Modifier.animateItemPlacement(tween(500))
							) {
								SwipeActionBox(item = task, onAction = {
									onEvent(HomeScreenEvent.OnSwipeTask(it))
									showCustomSnackbar(
										msg = "Task Deleted",
										actionText = "Undo",
										onClickAction = {
											onEvent(HomeScreenEvent.OnUndoDelete)
										})
								})
								{
									TaskComponent(
										task = task,
										onEdit = {
											onEvent(HomeScreenEvent.OnEditTask(it))
											onEditTask(it)
										},
										onComplete = {
											onEvent(HomeScreenEvent.OnCompleted(it, true))
										},
										onPomodoro = {
											onEvent(HomeScreenEvent.OnPomodoro(it))
											onPomodoroTask(it)
										},
										animDelay = index * Constants.LIST_ANIMATION_DELAY
									)
								}
							}
							Spacer(modifier = Modifier.height(10.dp))
						}
					}
				}
			}
		}
	}
}

@Preview
@Composable
fun HomeScreenPreview() {
	BingoTheme {
		val tasks = DummyTasks.dummyTasks
		HomeScreen(tasks = tasks, MainState(), {}, {}, {}, {}, {}, {}, {}, {}, {})
	}
}