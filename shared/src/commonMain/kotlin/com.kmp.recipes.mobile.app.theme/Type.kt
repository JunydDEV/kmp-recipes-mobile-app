package com.kmp.recipes.mobile.app.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import dev.icerock.moko.resources.compose.fontFamilyResource


@Composable
fun getTypography(): Typography {

    return Typography(
        headlineSmall = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = fontFamilyResource(SharedRes.fonts.Lato.bold),
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp
        ),
        titleLarge = TextStyle(
            fontWeight = FontWeight.Bold,
            fontFamily = fontFamilyResource(SharedRes.fonts.Lato.bold),
            fontSize = 20.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        bodyLarge = TextStyle(
            fontWeight = FontWeight.Normal,
            fontFamily = fontFamilyResource(SharedRes.fonts.Lato.regular),
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        ),
        bodyMedium = TextStyle(
            fontWeight = FontWeight.Medium,
            fontFamily = fontFamilyResource(SharedRes.fonts.Lato.medium),
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        ),
        labelMedium = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontFamily = fontFamilyResource(SharedRes.fonts.Lato.semibold),
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
    )
}