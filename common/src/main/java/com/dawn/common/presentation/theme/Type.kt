package com.dawn.common.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dawn.common.R

@Immutable
object CustomTypography {
    val TextRegular = TextStyle(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.Normal,
    )

    val TextMedium = TextStyle(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.Medium,
    )

    val TextSemiBold = TextStyle(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.SemiBold,
    )

    val TextBold = TextStyle(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.Bold,
    )

    val TextField = TextSemiBold.merge(
        fontSize = 16.sp
    )

    val Button = TextBold.merge(
        fontSize = 18.sp
    )
}

val sfProFontFamily = FontFamily(
    Font(R.font.sf_pro_font_family_regular, FontWeight.Normal),
    Font(R.font.sf_pro_font_family_medium, FontWeight.Medium),
    Font(R.font.sf_pro_font_family_semibold, FontWeight.SemiBold),
    Font(R.font.sf_pro_font_family_bold, FontWeight.Bold)
)