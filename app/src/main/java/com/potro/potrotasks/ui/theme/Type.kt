package com.potro.potrotasks.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.potro.potrotasks.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

/*val archivoRegular = FontFamily(Font(R.font.archivo_regular))//TODO: Cambiar a archivo_regular
val archivoLight = FontFamily(Font(R.font.archivo_light))//TODO: Cambiar a archivo_regular
val archivoMedium = FontFamily(Font(R.font.archivo_medium))//TODO: Cambiar a archivo_regular
val archivoBold = FontFamily(Font(R.font.archivo_bold))//TODO: Cambiar a archivo_regular

val interRegular = FontFamily(Font(R.font.inter_regular))//TODO: Cambiar a archivo_regular
val interLight = FontFamily(Font(R.font.inter_light))//TODO: Cambiar a archivo_regular
val interBold = FontFamily(Font(R.font.inter_bold))//TODO: Cambiar a archivo_regular
val interBlack = FontFamily(Font(R.font.inter_black))//TODO: Cambiar a archivo_regular
val interMedium = FontFamily(Font(R.font.inter_medium))//TODO: Cambiar a archivo_regular*/
