package com.example.featuresapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    modifier: Modifier = Modifier,
    yearFilterSelected: Boolean,
    ratingFilterSelected: Boolean,
    onDismiss: (filterApplied:FilterType?,applied:Boolean) -> Unit
) {
    val scope = rememberCoroutineScope()
    ModalBottomSheet(
        onDismissRequest = {
            onDismiss.invoke(null,false)
        },
        containerColor = Color.White,
        modifier = modifier.background(Color.Transparent)
    ) {
        Column {
            Text(text = "Apply Filter", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),modifier = Modifier.padding(start = 30.dp, top = 20.dp))
            CheckFilterRow(selected = yearFilterSelected, onClick = {
                scope.launch {
                    delay(500)
                    onDismiss.invoke(FilterType.YEAR,!yearFilterSelected)
                }
            }, filterText = "Year")

            CheckFilterRow(selected = ratingFilterSelected, onClick = {
                scope.launch {
                    delay(500)
                    onDismiss.invoke(FilterType.RATING, !ratingFilterSelected)
                }
            }, filterText = "Rating")
        }


    }
}

@Composable
fun CheckFilterRow(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    filterText: String
) {
    Row(modifier = Modifier.padding(top = 30.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = selected, onCheckedChange = {
            onClick.invoke()
        })
        Text(
            text = filterText, style = TextStyle(
                fontSize = 14.sp
            ), modifier = Modifier.padding(start = 15.dp)
        )
    }
}


enum class FilterType {
    YEAR,
    RATING
}