package com.ashutosh.bingo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ashutosh.bingo.Components.CustomSnackBar
import com.ashutosh.bingo.Navigation.AppNavigation
import com.ashutosh.bingo.ViewModel.TaskViewModel
import com.ashutosh.bingo.ui.theme.BingoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val taskViewModel by viewModels<TaskViewModel>()
//    private lateinit var notificationHelper: NotificationHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        //initializing the splash Screen using API
        installSplashScreen()

        // for loading the app state for App
        // taskViewModel.loadAppState(applicationContext)


        super.onCreate(savedInstanceState)
        setContent {
            BingoTheme(theme = taskViewModel.appState.theme) {
                AppNavigation(taskViewModel = taskViewModel)
                CustomSnackBar()
            }
        }
    }
}



