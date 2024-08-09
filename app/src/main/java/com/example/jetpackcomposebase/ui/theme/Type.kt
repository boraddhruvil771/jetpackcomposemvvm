package com.example.jetpackcomposebase.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposebase.R

private val Montserrat = FontFamily(
    Font(R.font.montserrat_regular),
    Font(R.font.montserrat_medium, FontWeight.W500)
)

val defaultTextStyle = TextStyle(
    fontFamily = Montserrat,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    ),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)

/**
 * Typography for material 3
 */
val JetnewsTypography = Typography(
    displayLarge = defaultTextStyle.copy(
        fontSize = 57.sp, lineHeight = 64.sp, letterSpacing = (-0.25).sp
    ),
    displayMedium = defaultTextStyle.copy(
        fontSize = 45.sp, lineHeight = 52.sp, letterSpacing = 0.sp
    ),
    displaySmall = defaultTextStyle.copy(
        fontSize = 36.sp, lineHeight = 44.sp, letterSpacing = 0.sp
    ),
    headlineLarge = defaultTextStyle.copy(
        fontSize = 32.sp, lineHeight = 40.sp, letterSpacing = 0.sp, lineBreak = LineBreak.Heading
    ),
    headlineMedium = defaultTextStyle.copy(
        fontSize = 28.sp, lineHeight = 36.sp, letterSpacing = 0.sp, lineBreak = LineBreak.Heading
    ),
    headlineSmall = defaultTextStyle.copy(
        fontSize = 24.sp, lineHeight = 32.sp, letterSpacing = 0.sp, lineBreak = LineBreak.Heading
    ),
    titleLarge = defaultTextStyle.copy(
        fontSize = 22.sp, lineHeight = 28.sp, letterSpacing = 0.sp, lineBreak = LineBreak.Heading
    ),
    titleMedium = defaultTextStyle.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
        fontWeight = FontWeight.Medium,
        lineBreak = LineBreak.Heading
    ),
    titleSmall = defaultTextStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        fontWeight = FontWeight.Medium,
        lineBreak = LineBreak.Heading
    ),
    labelLarge = defaultTextStyle.copy(
        fontSize = 14.sp, lineHeight = 20.sp, letterSpacing = 0.1.sp, fontWeight = FontWeight.Medium
    ),
    labelMedium = defaultTextStyle.copy(
        fontSize = 12.sp, lineHeight = 16.sp, letterSpacing = 0.5.sp, fontWeight = FontWeight.Medium
    ),
    labelSmall = defaultTextStyle.copy(
        fontSize = 11.sp, lineHeight = 16.sp, letterSpacing = 0.5.sp, fontWeight = FontWeight.Medium
    ),
    bodyLarge = defaultTextStyle.copy(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        lineBreak = LineBreak.Paragraph
    ),
    bodyMedium = defaultTextStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
        lineBreak = LineBreak.Paragraph
    ),
    bodySmall = defaultTextStyle.copy(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp,
        lineBreak = LineBreak.Paragraph
    ),
)

val Typography.bodyRegular: TextStyle
    get() = TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto_regular)),
        fontWeight = FontWeight(400),
    )
val Typography.body2Bold: TextStyle
    get() = TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto_bold)),
        fontWeight = FontWeight(400),
    )
val Typography.bodyBold: TextStyle
    get() = TextStyle(
        fontSize = 24.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto_bold)),
        fontWeight = FontWeight(700),
    )
val Typography.subheadRegular: TextStyle
    get() = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontFamily = FontFamily(Font(R.font.roboto_regular)),
        fontWeight = FontWeight(400),
    )
val Typography.subheadBold: TextStyle
    get() = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontFamily = FontFamily(Font(R.font.roboto_bold)),
        fontWeight = FontWeight(700),
    )
val Typography.body2Light: TextStyle
    get() = TextStyle(
        fontSize = 14.sp,
        lineHeight = 18.sp,
        fontFamily = FontFamily(Font(R.font.roboto_light)),
        fontWeight = FontWeight(300),
    )
val Typography.body2Regular: TextStyle
    get() = TextStyle(
        fontSize = 14.sp,
        lineHeight = 18.sp,
        fontFamily = FontFamily(Font(R.font.roboto_regular)),
        fontWeight = FontWeight(400),
    )
val Typography.captionRegular: TextStyle
    get() = TextStyle(
        fontSize = 10.sp,
        lineHeight = 12.sp,
        fontFamily = FontFamily(Font(R.font.roboto_regular)),
        fontWeight = FontWeight(400),
    )
val Typography.captionBold: TextStyle
    get() = TextStyle(
        fontSize = 10.sp,
        lineHeight = 12.sp,
        fontFamily = FontFamily(Font(R.font.roboto_bold)),

        )
val Typography.headline1Bold: TextStyle
    get() = TextStyle(
        fontSize = 20.sp,
        lineHeight = 24.sp,
        fontFamily = FontFamily(Font(R.font.roboto_bold)),
        fontWeight = FontWeight(700)
    )
val Typography.title2Bold: TextStyle
    get() = TextStyle(
        fontSize = 32.sp,
        lineHeight = 36.sp,
        fontFamily = FontFamily(Font(R.font.roboto_bold)),
        fontWeight = FontWeight(700),
    )
val Typography.title3Bold: TextStyle
    get() = TextStyle(
        fontSize = 24.sp,
        lineHeight = 28.sp,
        fontFamily = FontFamily(Font(R.font.roboto_bold)),
        fontWeight = FontWeight(700),
    )

val Typography.headline2Regular: TextStyle
    get() = TextStyle(
        fontSize = 18.sp,
        lineHeight = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto_regular)),
        fontWeight = FontWeight(400),
    )
val Typography.largeTitleBold: TextStyle
    get() = TextStyle(
        fontSize = 40.sp,
        lineHeight = 44.sp,
        fontFamily = FontFamily(Font(R.font.roboto_bold)),
        fontWeight = FontWeight(700),
    )
