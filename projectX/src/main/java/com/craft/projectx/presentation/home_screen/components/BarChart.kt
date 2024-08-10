package com.craft.projectx.presentation.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val defaultMaxHeight = 200.dp


@Composable
internal fun BarChart(
    modifier: Modifier = Modifier,
    values: List<Float>,
    maxHeight: Dp = defaultMaxHeight
) {

    assert(values.isNotEmpty()) { "Input values are empty" }
    var selectedValue by remember { mutableStateOf<Float?>(null) }


    val borderColor = Color.Black
    val density = LocalDensity.current
    val strokeWidth = with(density) { 1.dp.toPx() }

    Row(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .height(maxHeight)
                .drawBehind {
                    drawLine(
                        alpha = .1f,
                        color = Color.Black,
                        start = Offset(0f, size.height+40f),
                        end = Offset(size.width, size.height+40f),
                        strokeWidth = strokeWidth
                    )
                },
        ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        values.forEach { item ->
            Bar(
                value = item,
                color = Color(0xFF3655FC),
                maxHeight = maxHeight,
                isSelected = selectedValue == item,
                onClick = { selectedValue = item }
            )
        }
    }

}

@Composable
private fun RowScope.Bar(
    value: Float,
    color: Color,
    maxHeight: Dp,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val itemHeight = remember(value) { value * maxHeight.value / 100 }

    if (isSelected) {
//        Text(
//            text = value.toString(),
//            style = TextStyle(fontSize = 12.sp, color = Color.Black)
//        )
    }

    Box(
        modifier = Modifier
            .padding(horizontal = 1.dp)
            .height(itemHeight.dp)
            .weight(1f)
            .background(color)
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable { onClick() }
    )

}