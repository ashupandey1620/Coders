package com.ashutosh.bingo

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.ashutosh.bingo.worker.RepeatTaskWorker
import dagger.hilt.android.HiltAndroidApp
import java.time.LocalTime
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class BingoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initWorker()
    }

    private fun initWorker() {
        val maxTimeSec = LocalTime.MAX.toSecondOfDay() + 1
        val currentTimeSec = LocalTime.now().toSecondOfDay()

        val delay = (maxTimeSec - currentTimeSec)
        if (delay > 0) {
            startRepeatWorker(delay)
        }
    }


    private fun startRepeatWorker(delay: Int) {
        // repeat task request
        val workRequest =
            PeriodicWorkRequest.Builder(RepeatTaskWorker::class.java, 1, TimeUnit.DAYS)
                .setInitialDelay(delay.toLong(), TimeUnit.SECONDS)
                .build()

        WorkManager.getInstance(applicationContext)
            .enqueueUniquePeriodicWork(
                "Repeat-Tasks",
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
    }


}