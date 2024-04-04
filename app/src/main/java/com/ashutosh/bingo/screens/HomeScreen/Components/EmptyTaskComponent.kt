package com.ashutosh.bingo.screens.HomeScreen.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ashutosh.bingo.Components.h1TextStyle
import com.ashutosh.bingo.R


@Composable
fun EmptyTaskComponent(modifier: Modifier = Modifier) {
	Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
	{
		Column(
			modifier = modifier.size(250.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.spacedBy(24.dp)
		) {
			Image(
				painter = painterResource(id = R.drawable.no_tasks),
				contentDescription = null
			)
			Text(
				text = "No Tasks",
				style = h1TextStyle,
				color = MaterialTheme.colorScheme.onPrimary
			)
		}
	}
}

@Preview
@Composable
private fun EmptyTaskComponentPreview() {
	EmptyTaskComponent()
}