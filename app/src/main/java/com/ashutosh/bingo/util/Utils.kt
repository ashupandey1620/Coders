package com.ashutosh.bingo.util

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.content.Intent
import android.net.Uri
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import java.time.LocalTime

fun vibrateDevice(
	context: Context,
	duration: Long = 500,
	vibrationEffect: Int = VibrationEffect.DEFAULT_AMPLITUDE
) {
	val vibrator = context.getSystemService(VIBRATOR_SERVICE) as Vibrator?

	if (vibrator?.hasVibrator() == true) {
		vibrator.vibrate(VibrationEffect.createOneShot(duration, vibrationEffect))
	}
}

fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_LONG) {
	Toast.makeText(context, message, duration).show()
}


fun getFormattedDuration(
	startTime: LocalTime,
	endTime: LocalTime
): String {
	val taskDuration = endTime.toSecondOfDay() - startTime.toSecondOfDay()

	val hours = taskDuration / 3600
	val minutes = (taskDuration % 3600) / 60

	val hoursString = if (hours == 1) "hour" else "hours"

	return when {
		hours > 0 && minutes > 0 -> String.format("%dh %02dm", hours, minutes)
		hours > 0 -> String.format("%d $hoursString", hours)
		else -> String.format("%dmin", minutes)
	}

}

fun getFreeTime(totalDuration: Long): String {
	val maxTime = LocalTime.MAX.toSecondOfDay()
	val currentTime = LocalTime.now().toSecondOfDay()

	val totalFreeDuration = maxTime - currentTime - totalDuration

	val hours = (totalFreeDuration / 3600).toInt()
	val minutes = ((totalFreeDuration % 3600) / 60).toInt()


	val hoursString = if (hours == 1) "hour" else "hours"

	return when {
		hours > 0 && minutes > 0 -> String.format("%dh %02dm", hours, minutes)
		hours > 0 -> String.format("%d $hoursString", hours)
		else -> String.format("%d min", minutes)
	}
}

