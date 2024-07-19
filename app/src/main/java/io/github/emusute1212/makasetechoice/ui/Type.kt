package io.github.emusute1212.makasetechoice.ui

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.github.emusute1212.makasetechoice.R

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.noto_sansjp_medium)),
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.noto_sansjp_bold)),
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.noto_sansjp_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.noto_sansjp_medium)),
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    )
)