package com.ashutosh.bingo.screens.HomeScreen.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ashutosh.bingo.Components.infoDescTextStyle
import com.ashutosh.bingo.Components.infoTextStyle
import com.ashutosh.bingo.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoComponent(
	title: String,
	desc: String,
	icon: Int,
	backgroundColor: Color,
	modifier: Modifier,
	onClick: () -> Unit
) {
	Card(
		modifier = modifier,
		shape = RoundedCornerShape(16.dp),
		colors = CardDefaults.cardColors(containerColor = backgroundColor),
		onClick = { onClick() }
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(
					8.dp,
					16.dp
				),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {

			Text(
				text = title,
				style = infoTextStyle,
				color = Black
			)

			Spacer(modifier = Modifier.height(4.dp))
			Row(
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.spacedBy(8.dp)
			) {
				Icon(
					painter = painterResource(id = icon),
					contentDescription = null,
					tint = Black,
					modifier = Modifier.size(20.dp)
				)
				Text(
					text = desc,
					style = infoDescTextStyle,
					color = Black
				)
			}
		}
	}
}

@Preview
@Composable
private fun InfoComponentPreview() {
	InfoComponent(
		title = "Completed",
		desc = "1/3 Tasks",
		icon = R.drawable.ic_task_list,
		backgroundColor = Blue,
		modifier = Modifier,
		{}
	)
}