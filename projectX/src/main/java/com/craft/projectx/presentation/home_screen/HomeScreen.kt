package com.craft.projectx.presentation.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.craft.projectx.data.UsageData
import com.craft.projectx.presentation.common.h1TextStyle
import com.craft.projectx.presentation.common.h2TextStyle
import com.craft.projectx.presentation.common.h3TextStyle
import com.craft.projectx.presentation.common.h4TextStyle
import com.craft.projectx.presentation.home_screen.components.BarChart
import com.craft.projectx.presentation.home_screen.components.StartButton
import com.craft.projectx.utils.DummyUsage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    usageData: List<UsageData> = DummyUsage.dummyUsage
) {
    var active by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                },
            )
        },
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .fillMaxWidth()
                    .padding(paddingValues) // Apply scaffold padding to content
            ) {
                Box(
                    modifier = Modifier
                        .height(125.dp)
                        .padding(all = 16.dp)
                ) {
                    StartButton(
                        onCheckedChange = { checked ->
                            active = checked
                        }
                    )
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)

                ) {
                    Text(
                        text = "Your Status is",
                        color = Color.Black,
                        style = h2TextStyle
                    )
                }
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(all = 8.dp)
                ) {
                    val itemText = if (active) "Active" else "not Active"
                    val color = if (active) Color.LightGray else Color.Black

                    Text(
                        text = itemText,
                        color = color,
                        style = h1TextStyle
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(usageData) { data ->
                        UsageItem(data,true)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "All Apps",
                        style = h1TextStyle,
                        color = Color.Black,
                    )
                    Text(
                        text = "Video Played",
                        style = h4TextStyle,
                        color = Color.Black,
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(usageData) { data ->
                        UsageItem(data,false)
                    }
                }

                val time = listOf<Float>(10f, 20f, 40f, 15f, 60f, 20f, 10f)
                Box(modifier = Modifier.padding(horizontal = 30.dp)) {
                    BarChart(modifier = Modifier, time)
                }


            }
        }
    )
}

@Composable
fun UsageItem(data: UsageData, showLinearProgressBar:Boolean) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .padding(vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = data.appName,
                style = h3TextStyle,
                color = Color.Black,
            )
            Text(
                text = data.time.toString()+" mins",
                style = h3TextStyle,
                color = Color.Black,
            )
        }
        if(showLinearProgressBar) {
            LinearProgressIndicator(
                progress = { data.timeUsed },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp),
                color = Color.Black,
                trackColor = Color.Transparent
            )
        }
        
    }
}
