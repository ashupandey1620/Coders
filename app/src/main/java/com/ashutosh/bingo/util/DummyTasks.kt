package com.ashutosh.bingo.util

import com.ashutosh.bingo.domain.models.Task
import java.time.LocalDate
import java.time.LocalTime
import kotlin.random.Random

object DummyTasks {

	val dummyTasks = generateDummyTasks()

	private fun generateDummyTasks(count: Int = 5): List<Task> {
		val tasks = mutableListOf<Task>()

		for (i in 1..count) {
			val task = Task(
				id = i,
				uuid = "$i",
				title = "Task $i",
				isCompleted = Random.nextBoolean(),
				startTime = LocalTime.now(),
				endTime = LocalTime.now().plusHours(Random.nextLong(1, 4)),
				reminder = Random.nextBoolean(),
				isRepeated = Random.nextBoolean(),
				repeatWeekdays = "",
				pomodoroTimer = -1,
				priority = Random.nextInt(3),
				date = LocalDate.now()
			)
			tasks.add(task)
		}
		return tasks
	}
}