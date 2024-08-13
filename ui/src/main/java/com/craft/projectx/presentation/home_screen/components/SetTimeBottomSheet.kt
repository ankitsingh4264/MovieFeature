package com.craft.projectx.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.craft.projectx.presentation.common.h1TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetTimeBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onTimeSet: (time: Float) -> Unit
) {
    var time by remember { mutableStateOf(0f) }

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss.invoke()
        },
        containerColor = Color.White,
        modifier = modifier.background(Color.Transparent)
    ) {
        Column {
            Text(
                text = "Set Daily Cap",
                style = h1TextStyle,
                modifier = Modifier.padding(start = 30.dp, top = 20.dp)
            )

            Box(modifier = Modifier.padding(all = 30.dp)) {
                TimeSlider(onTimeSet = {
                    time = it
                })
            }
            onTimeSet(time)
            Box(
                modifier = Modifier.padding(all = 16.dp)
                    .align(alignment = Alignment.CenterHorizontally)

            ) {
                Text(
                    text = time.toString(),
                    color = Color.Black,
                    style = h1TextStyle
                )
            }

        }
    }
}