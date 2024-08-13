package com.craft.projectx.presentation.home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.craft.projectx.R
import com.craft.projectx.data.UsageData

@Composable
fun StartButton(
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.start_lottie))
    var checked by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }

    val progress by animateLottieCompositionAsState(
        composition = composition,
        restartOnPlay = false,
        isPlaying = isPlaying,
        speed = if (checked) -2f else 2f,
        clipSpec = LottieClipSpec.Progress(0f, 1f)
    )

    LottieAnimation(
        composition,
        progress = {
            progress
        },
        modifier = modifier.clickable {
            isPlaying = true
        }
    )

    LaunchedEffect(progress) {
        if (isPlaying &&
            (progress == 1.0f || progress == 0.0f)
        ) {
            isPlaying = false
            checked = !checked
            onCheckedChange(checked)
        }
    }
}