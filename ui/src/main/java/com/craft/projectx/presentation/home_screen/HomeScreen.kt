package com.craft.projectx.presentation.home_screen

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.craft.projectx.R
import com.craft.projectx.UiCallback
import com.craft.projectx.data.UsageData
import com.craft.projectx.presentation.common.cardBackgroundColor
import com.craft.projectx.presentation.common.h1TextStyle
import com.craft.projectx.presentation.common.h4TextStyle
import com.craft.projectx.presentation.common.h5TextStyle
import com.craft.projectx.presentation.common.h6TextStyle
import com.craft.projectx.presentation.home_screen.components.BarChart
import com.craft.projectx.presentation.home_screen.components.SetTimeBottomSheet
import com.craft.projectx.presentation.home_screen.components.StartButton
import com.craft.projectx.utils.DummyUsage

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    usageData: List<UsageData> = DummyUsage.dummyUsage,
    uiCallbacks: (UiCallback) -> Unit
) {

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                ActiveButtonCard()
                DailyCapCard()
                TimeUsageCard()
                AppSelectCard(usageData, uiCallbacks)
                WeeklyGraphCard()
            }
        }
    )
}

@Composable
fun ActiveButtonCard() {
    var active by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .padding(vertical = 10.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = cardBackgroundColor,
            )
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
                    style = h5TextStyle
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
        }
    }
}

@Composable
fun DailyCapCard() {
    var showBs by remember { mutableStateOf(false) }
    var time by remember { mutableStateOf(0f) }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .padding(vertical = 10.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = cardBackgroundColor,
            )
        ) {

            Box(
                modifier = Modifier
                    .padding(all = 8.dp)
            ) {
                Text(
                    text = "Daily Cap",
                    color = Color.Black,
                    style = h5TextStyle
                )
            }
            Box(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth()

            ) {
                Text(
                    modifier = Modifier
                        .align(alignment = Alignment.Center),
                    text = time.toString(),
                    color = Color.Black,
                    style = h1TextStyle
                )
            }

            Button(
                onClick = {
                    showBs = true

                },
                Modifier
                    .padding(all = 8.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            ) {
                Text(text = "Set Daily Cap")
            }

            if (showBs) {
                SetTimeBottomSheet(onTimeSet = {
                    time = it
                },
                    onDismiss = {
                        showBs = false
                    })
            }
        }
    }
}

@Composable
fun TimeUsageCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .padding(vertical = 10.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = cardBackgroundColor,
            )
        ) {

            Box(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth()

            ) {
                Text(
                    text = "Total Time Used (Today)",
                    color = Color.Black,
                    style = h5TextStyle
                )
            }
            Box(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth()

            ) {
                Text(
                    modifier = Modifier
                        .align(alignment = Alignment.Center),
                    text = "2h 3mins",
                    color = Color.Black,
                    style = h1TextStyle
                )
            }
        }
    }
}

@Composable
fun AppSelectCard(usageData: List<UsageData>, uiCallbacks: (UiCallback) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .padding(vertical = 10.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = cardBackgroundColor,
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(vertical = 16.dp),
            ) {
                Text(
                    text = "Apps",
                    style = h1TextStyle,
                    color = Color.Black,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                for (data in usageData) {
                    AppsSelectCardDetail(data, uiCallbacks)
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}

@Composable
fun AppsSelectCardDetail(data: UsageData, uiCallbacks: (UiCallback) -> Unit) {
    val selectState = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                when (data.appName) {
                    "Instagram" -> {
                        Icon(
                            modifier = Modifier.padding(end = 8.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable._202852_instagram_icon),
                            contentDescription = null
                        )
                    }

                    "Youtube" -> {
                        Icon(
                            modifier = Modifier.padding(end = 8.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable._964418_logo_media_play_social_youtube_icon),
                            contentDescription = null
                        )
                    }

                    "Snapchat" -> {
                        Icon(
                            modifier = Modifier.padding(end = 8.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable._964410_logo_media_snapchat_social_icon),
                            contentDescription = null
                        )
                    }

                    else -> {
                    }
                }
                Column {
                    Text(
                        text = data.appName,
                        style = h4TextStyle,
                        color = Color.Black,
                    )
                    Text(
                        text = data.time.toString() + "mins",
                        style = h6TextStyle,
                        color = Color.Black,
                    )
                }


            }
            Checkbox(
                checked = selectState.value,
                onCheckedChange = {
                    selectState.value = it
                    uiCallbacks(UiCallback.AddPackage(data.appName, it))
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Black,
                    uncheckedColor = Color.Black,
                    checkmarkColor = Color.White
                )
            )
        }
    }
}

@Composable
fun WeeklyGraphCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .padding(vertical = 10.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = cardBackgroundColor,
            )
        ) {
            val time = listOf<Float>(10f, 20f, 40f, 15f, 60f, 20f, 10f)
            Box(modifier = Modifier.padding(horizontal = 30.dp)) {
                BarChart(modifier = Modifier, time)
            }
        }
    }
}