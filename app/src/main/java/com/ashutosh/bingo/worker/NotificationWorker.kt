package com.ashutosh.bingo.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.ashutosh.bingo.util.Constants
import com.ashutosh.bingo.util.NotificationHelper


class NotificationWorker(val context: Context, params: WorkerParameters) :
	Worker(context, params) {

	private val notificationHelper = NotificationHelper(context)

	override fun doWork(): Result {
		//get required data
		val taskUUID = inputData.getString(Constants.TASK_UUID)
		val taskTitle = inputData.getString(Constants.TASK_TITLE)
		val taskTime = inputData.getString(Constants.TASK_TIME)

		if (taskUUID != null && taskTime != null && taskTitle != null) {
			notificationHelper.showNotification(
				taskUUID.hashCode(),
				taskTitle,
				taskTime
			)
			return Result.success()
		}
		return Result.failure()
	}
}