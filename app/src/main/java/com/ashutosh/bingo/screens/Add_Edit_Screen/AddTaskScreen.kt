package com.ashutosh.bingo.screens.Add_Edit_Screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Today
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashutosh.bingo.CommonScreens.Priority
import com.ashutosh.bingo.CommonScreens.ShowTimePicker
import com.ashutosh.bingo.Components.SnackbarController.showCustomSnackbar
import com.ashutosh.bingo.Components.h1TextStyle
import com.ashutosh.bingo.Components.h2TextStyle
import com.ashutosh.bingo.Components.h3TextStyle
import com.ashutosh.bingo.Components.taskTextStyle
import com.ashutosh.bingo.ViewModel.AppTheme
import com.ashutosh.bingo.ViewModel.MainState
import com.ashutosh.bingo.domain.models.Task
import com.ashutosh.bingo.screens.Add_Edit_Screen.Components.CustomDatePickerDialog
import com.ashutosh.bingo.screens.Add_Edit_Screen.Components.CustomDurationDialogComponent
import com.ashutosh.bingo.screens.Add_Edit_Screen.Components.DurationComponent
import com.ashutosh.bingo.screens.Add_Edit_Screen.Components.PriorityComponent
import com.ashutosh.bingo.screens.Add_Edit_Screen.Components.WeekDaysComponent
import com.ashutosh.bingo.ui.theme.BingoTheme
import com.ashutosh.bingo.ui.theme.Blue
import com.ashutosh.bingo.ui.theme.DarkGreen
import com.ashutosh.bingo.ui.theme.LightGreen
import com.ashutosh.bingo.ui.theme.Red
import com.ashutosh.bingo.ui.theme.priorityColors
import com.ashutosh.bingo.util.checkValidTask
import com.ashutosh.bingo.util.getFormattedDuration
import kotlinx.coroutines.job
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    appState: MainState,
    onEvent: (AddEditScreenEvent) -> Unit,
    onBack: () -> Unit
) {

    var taskTitle by remember { mutableStateOf("") }
    var taskStartTime by remember { mutableStateOf(LocalTime.now()) }
    var taskEndTime by remember { mutableStateOf(LocalTime.now().plusHours(1)) }
    var taskDate by remember { mutableStateOf(LocalDate.now()) }
    var isTaskReminderOn by remember { mutableStateOf(true) }
    var isTaskRepeated by remember { mutableStateOf(false) }
    var repeatedWeekDays by remember { mutableStateOf("") }
    var taskPriority by remember { mutableStateOf(Priority.LOW) }
    val taskDuration by remember { mutableLongStateOf(60) }
    var isTimeUpdated by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val focusRequester = FocusRequester()

    var showDialogCustomDuration by remember { mutableStateOf(false) }
    var showDialogDatePicker by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            title = {
                Text(
                    text = "Add Task" ,
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
                    modifier = Modifier
                        .clickable { showDialogDatePicker = true }
                        .border(2.dp, MaterialTheme.colorScheme.secondary, RoundedCornerShape(8.dp))
                        .padding(top = 8.dp, bottom = 8.dp, end = 10.dp, start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val dtf = DateTimeFormatter.ofPattern("d MMMM")
                    Icon(imageVector = Icons.Default.Today, contentDescription = null)
                    Text(
                        text = taskDate.format(dtf),
                        style = h3TextStyle
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        )
    }) { innerPadding ->

        LaunchedEffect(key1 = true,
            block = {
                coroutineContext.job.invokeOnCompletion {
                    focusRequester.requestFocus()
                }
            })


        // confirm delete dialog
        if (showDialogCustomDuration) {
            CustomDurationDialogComponent(
                onClose = { showDialogCustomDuration = false },
                onSelect = { time ->
                    val duration = time.toSecondOfDay() / 60L
                    taskEndTime = taskStartTime.plusMinutes(duration)
                    isTimeUpdated = !isTimeUpdated
                }
            )
        }

        if (showDialogDatePicker) {
            CustomDatePickerDialog(
                defaultDay = taskDate, onClose = { day ->
                    taskDate = day
                    showDialogDatePicker = false
                })
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp, 8.dp)
                        .background(priorityColors[taskPriority.ordinal], RoundedCornerShape(8.dp))
                ) {

                    TextField(
                        value = taskTitle,
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.secondary,
                            unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                            disabledContainerColor = MaterialTheme.colorScheme.secondary,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            cursorColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                        textStyle = taskTextStyle,
                        onValueChange = {
                            taskTitle = it
                        },
                        placeholder = {
                            Text(
                                text = "What would you like to do?" ,
                                color = MaterialTheme.colorScheme.onSecondary
                            )
                        },
                        shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp) ,
                        modifier = Modifier
                            .focusRequester(focusRequester)
                            .fillMaxWidth()
                            .padding(start = 8.dp),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            imeAction = ImeAction.Done
                        )
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp, 8.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Start Time" ,
                            style = taskTextStyle,
                            color = if (appState.theme == AppTheme.Light) DarkGreen else LightGreen
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        ShowTimePicker(
                            time = taskStartTime
                        ) { snappedTime ->
                            taskStartTime = snappedTime
                        }
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "End Time" ,
                            style = taskTextStyle,
                            color = Red
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        ShowTimePicker(
                            time = taskEndTime,
                            isTimeUpdated = isTimeUpdated
                        ) { snappedTime ->
                            taskEndTime = snappedTime
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, end = 32.dp, top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Duration" ,
                        style = h2TextStyle,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Text(
                        text = getFormattedDuration(taskStartTime, taskEndTime),
                        style = taskTextStyle,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                DurationComponent(
                    modifier = Modifier
                        .padding(horizontal = 24.dp),
                    durationList = appState.durationList,
                    defaultDuration = taskDuration
                ) { duration ->
                    if (duration == 0L) {
                        showDialogCustomDuration = true
                    } else {
                        taskEndTime = taskStartTime.plusMinutes(duration)
                        isTimeUpdated = !isTimeUpdated
                    }
                }

                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp, 0.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Notifications, contentDescription = null)
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 4.dp),
                            text = "Reminder" ,
                            style = h2TextStyle,
                            color = MaterialTheme.colorScheme.onPrimary
                        )

                        Switch(
                            checked = isTaskReminderOn,
                            onCheckedChange = { isTaskReminderOn = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Blue,
                                checkedTrackColor = MaterialTheme.colorScheme.secondary,
                                uncheckedTrackColor = MaterialTheme.colorScheme.secondary
                            )
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp, 0.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 4.dp),
                            text = "Repeat" ,
                            style = h2TextStyle,
                            color = MaterialTheme.colorScheme.onPrimary
                        )

                        Switch(
                            checked = isTaskRepeated,
                            onCheckedChange = { isTaskRepeated = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Blue,
                                checkedTrackColor = MaterialTheme.colorScheme.secondary,
                                uncheckedTrackColor = MaterialTheme.colorScheme.secondary
                            )
                        )
                    }

                    AnimatedVisibility(visible = isTaskRepeated) {
                        val dayOfWeek = LocalDate.now().dayOfWeek.value - 1
                        WeekDaysComponent(
                            defaultRepeatedDays = listOf(dayOfWeek),
                            onChange = { repeatedWeekDays = it }
                        )
                    }


                }
                Divider(
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = MaterialTheme.colorScheme.secondary
                )
                PriorityComponent {
                    taskPriority = it
                }
            }

            //bottom action buttons
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {
                        val task = Task(
                            id = 0,
                            uuid = UUID.randomUUID().toString(),
                            title = taskTitle.trim(),
                            isCompleted = false,
                            startTime = taskStartTime,
                            endTime = taskEndTime,
                            reminder = isTaskReminderOn,
                            isRepeated = isTaskRepeated,
                            repeatWeekdays = repeatedWeekDays,
                            pomodoroTimer = -1,
                            date = taskDate,
                            priority = taskPriority.ordinal
                        )

                        val (isValid, errorMessage) = checkValidTask(
                            task = task,
                            totalTasksDuration = appState.totalTaskDuration
                        )

                        if (isValid) {
                            onEvent(AddEditScreenEvent.OnAddTaskClick(task))
                            showCustomSnackbar(
                                "Task Added Successfully",
                                actionColor = LightGreen
                            )
                            onBack()
                        } else {
                            showCustomSnackbar(errorMessage)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(16.dp) ,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Add Task" ,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Preview()
@Composable
fun AddTaskScreenPreview() {
    BingoTheme {
        AddTaskScreen(MainState() , onEvent = {}, {})
    }
}