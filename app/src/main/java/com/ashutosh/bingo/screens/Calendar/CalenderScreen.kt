package com.ashutosh.bingo.screens.Calendar

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material.icons.filled.ViewWeek
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ashutosh.bingo.CommonScreens.filterTasksByDate
import com.ashutosh.bingo.Components.h1TextStyle
import com.ashutosh.bingo.domain.models.Task
import com.ashutosh.bingo.screens.Calendar.Components.DaysOfWeekTitle
import com.ashutosh.bingo.screens.Calendar.Components.MonthDayComponent
import com.ashutosh.bingo.screens.Calendar.Components.WeekDayComponent
import com.ashutosh.bingo.screens.HomeScreen.Components.EmptyTaskComponent
import com.ashutosh.bingo.screens.HomeScreen.Components.TaskComponent
import com.ashutosh.bingo.screens.HomeScreen.HomeScreenEvent
import com.ashutosh.bingo.ui.theme.BingoTheme
import com.ashutosh.bingo.util.Constants
import com.ashutosh.bingo.util.DummyTasks
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CalenderScreen(
	tasks: List<Task> ,
	onEvent: (HomeScreenEvent) -> Unit ,
	onEditTask: (id: Int) -> Unit ,
	onPomodoroTask: (id: Int) -> Unit ,
	onBack: () -> Unit
) {
	val scope = rememberCoroutineScope()
	var isWeekCalender by remember { mutableStateOf(false) }
	val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }

	// monthly calender
	val currentMonth = remember { YearMonth.now() }
	val startMonth = remember { currentMonth.minusMonths(50) }
	val endMonth = remember { currentMonth.plusMonths(50) }
	val monthState = rememberCalendarState(
		startMonth = startMonth,
		endMonth = endMonth,
		firstVisibleMonth = currentMonth,
		firstDayOfWeek = firstDayOfWeek
	)

	// weekly calender
	val currentDate = remember { LocalDate.now() }
	val startDate = remember { currentDate.minusDays(100) }
	val endDate = remember { currentDate.plusDays(100) }
	val weekState = rememberWeekCalendarState(
		startDate = startDate,
		endDate = endDate,
		firstDayOfWeek = firstDayOfWeek
	)

	var selectedDay by remember { mutableStateOf<LocalDate>(currentDate) }
	var currentMonthTitle by remember { mutableStateOf(currentMonth.month) }
	currentMonthTitle = if (isWeekCalender) weekState.firstVisibleWeek.days[0].date.month
	else monthState.lastVisibleMonth.yearMonth.month

	Scaffold(topBar = {
		TopAppBar(
			colors = TopAppBarDefaults.topAppBarColors(
				containerColor = MaterialTheme.colorScheme.background,
			),
			title = {
				Text(
					text = currentMonthTitle.getDisplayName(TextStyle.FULL, Locale.getDefault()),
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
				IconButton(onClick = {
					scope.launch {
						selectedDay = currentDate
						if (isWeekCalender)
							weekState.animateScrollToWeek(currentDate)
						else
							monthState.animateScrollToMonth(currentMonth)
					}
				}) {
					Icon(imageVector = Icons.Default.Restore, contentDescription = null)
				}

				IconButton(onClick = { isWeekCalender = !isWeekCalender }) {
					val currentIcon =
						if (isWeekCalender) Icons.Default.CalendarMonth else Icons.Default.ViewWeek
					Icon(imageVector = currentIcon, contentDescription = null)
				}
			}
		)
	}) { innerPadding ->

		Column(modifier = Modifier.padding(innerPadding)) {

			AnimatedVisibility(visible = isWeekCalender) {
				WeekCalendar(
					modifier = Modifier.padding(horizontal = 10.dp),
					state = weekState,
					dayContent = { day ->
						WeekDayComponent(
							day,
							selected = selectedDay == day.date,
							indicator = filterTasksByDate(tasks, day.date).isNotEmpty()
						) {
							selectedDay = it
						}
					},
				)
			}

			AnimatedVisibility(visible = !isWeekCalender) {
				HorizontalCalendar(
					modifier = Modifier.padding(horizontal = 10.dp),
					state = monthState,
					dayContent = { day ->
						MonthDayComponent(
							day,
							selected = selectedDay == day.date,
							indicator = filterTasksByDate(tasks, day.date).isNotEmpty()
						) {
							selectedDay = it
						}
					},
					monthHeader = { month ->
						val daysOfWeek = month.weekDays.first().map { it.date.dayOfWeek }
						DaysOfWeekTitle(daysOfWeek = daysOfWeek)
					}
				)
			}

			Divider(
				modifier = Modifier.padding(vertical = 24.dp),
				thickness = 2.dp,
				color = MaterialTheme.colorScheme.secondary
			)

			val selectedDayTasks = filterTasksByDate(tasks, selectedDay)
			if (selectedDayTasks.isEmpty()) {
				EmptyTaskComponent()
			} else {
				LazyColumn(
					modifier = Modifier
						.fillMaxSize()
						.padding(16.dp, 0.dp)
				) {
					itemsIndexed(items = selectedDayTasks,
						key = { index, task ->
							task.id
						}) { index, task ->
						Box(
							modifier = Modifier.animateItemPlacement(tween(500))
						) {
							TaskComponent(
								task = task,
								onEdit = {
									if (task.date >= LocalDate.now()) {
										onEvent(HomeScreenEvent.OnEditTask(it))
										onEditTask(it)
									}
								},
								onComplete = {
									if (task.date >= LocalDate.now()) {
										onEvent(HomeScreenEvent.OnCompleted(it, !task.isCompleted))
									}
								},
								onPomodoro = {
									onEvent(HomeScreenEvent.OnPomodoro(it))
									onPomodoroTask(it)
								},
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
fun CalenderScreenPreview() {
	BingoTheme {
		val tasks = DummyTasks.dummyTasks
		CalenderScreen(tasks, {}, {}, {}, {})
	}
}