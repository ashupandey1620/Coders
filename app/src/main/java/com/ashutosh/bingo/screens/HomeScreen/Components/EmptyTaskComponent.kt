package com.ashutosh.bingo.screens.HomeScreen.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashutosh.bingo.Components.h1TextStyle
import com.ashutosh.bingo.R


@Composable
fun EmptyTaskComponent(modifier: Modifier = Modifier) {
	Column(
		modifier = Modifier.fillMaxSize() ,
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {


		Image(
			painter = painterResource(id = R.drawable.nodata) ,
			contentDescription = "nodata" ,
			modifier = Modifier.size(200.dp)
		)

		Text(
			modifier = Modifier
				.padding(horizontal = 30.dp)
				.fillMaxWidth() ,
			textAlign = TextAlign.Center,
			fontSize = 20.sp ,
			lineHeight = 20.sp ,
			text = "No Data to Show",
			fontWeight = FontWeight.Medium,
			color = Color(0xFFfdc044)
			)

	}
}

@Preview
@Composable
private fun EmptyTaskComponentPreview() {
	EmptyTaskComponent()
}