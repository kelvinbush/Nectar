package com.kelvinbush.nectar.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.kelvinbush.nectar.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontSize = 48.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.White,
        lineHeight = 29.sp,
        letterSpacing = 0.sp,
        fontFamily = FontFamily(Font(R.font.gilroysemibold, weight = FontWeight.SemiBold))
    ),
    h3 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = Color.White,
        lineHeight = 15.sp,
        letterSpacing = 0.sp,
        fontFamily = FontFamily(Font(R.font.gilroymedium, weight = FontWeight.Medium))
    ),
    button = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.White,
        lineHeight = 18.sp,
        letterSpacing = 0.sp,
        fontFamily = FontFamily(Font(R.font.gilroysemibold, weight = FontWeight.SemiBold))
    ),
    h2 = TextStyle(
        fontSize = 26.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xff030303),
        lineHeight = 29.sp,
        letterSpacing = 0.sp,
        fontFamily = FontFamily(Font(R.font.gilroysemibold, weight = FontWeight.SemiBold)),
        textAlign = TextAlign.Start
    ),
    h4 = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xff030303),
        lineHeight = 29.sp,
        letterSpacing = 0.sp,
        fontFamily = FontFamily(Font(R.font.gilroysemibold, weight = FontWeight.SemiBold)),
        textAlign = TextAlign.Start
    ),
    caption = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xff828282),
        lineHeight = 0.sp,
        letterSpacing = 0.sp,
        fontFamily = FontFamily(Font(R.font.gilroysemibold, weight = FontWeight.SemiBold)),
        textAlign = TextAlign.Center
    ),
    h6 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xff181725),
        lineHeight = 1.08.sp,
        letterSpacing = 0.sp,
        fontFamily = FontFamily(Font(R.font.gilroymedium, weight = FontWeight.SemiBold)),
        textAlign = TextAlign.End
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)