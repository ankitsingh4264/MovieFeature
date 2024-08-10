package com.craft.projectx.presentation.common

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.craft.projectx.R

val fontMontserrat = FontFamily(Font(R.font.montserrat))
val fontRobotoMonoThin = FontFamily(Font(R.font.roboto_mono_thin))
val fontRoboto = FontFamily(Font(R.font.roboto))



val durationTextStyle = TextStyle(
    fontSize = 20.sp,
    fontFamily = fontMontserrat
)

var h1TextStyle = TextStyle(
    fontSize = 24.sp,
    fontFamily = fontMontserrat,
    fontWeight = FontWeight.Bold
)

var h2TextStyle = TextStyle(
    fontSize = 20.sp,
    fontFamily = fontRobotoMonoThin,
    fontWeight = FontWeight.Bold

)
var h3TextStyle = TextStyle(
    fontSize = 16.sp,
    fontFamily = fontRobotoMonoThin,
    fontWeight = FontWeight.Bold
)

val h4TextStyle = TextStyle(
    fontSize = 18.sp,
    fontFamily = fontMontserrat,
    fontWeight = FontWeight.SemiBold
)

val infoDescTextStyle = TextStyle(
    fontSize = 14.sp,
    fontFamily = fontMontserrat
)

var taskDescTextStyle = TextStyle(
    fontSize = 12.sp,
    fontFamily = fontMontserrat
)

var taskTextStyle = TextStyle(
    fontSize = 16.sp,
    fontFamily = fontMontserrat,
)

var timerTextStyle = TextStyle(
    fontSize = 42.sp,
    fontFamily = fontMontserrat
)

var settingItemTextStyle = TextStyle(
    fontSize = 18.sp,
    fontFamily = fontMontserrat,
)