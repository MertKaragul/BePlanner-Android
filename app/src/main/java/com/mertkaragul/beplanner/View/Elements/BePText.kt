package com.mertkaragul.beplanner.View.Elements

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun BePText(
    text : String,
    fontSize : TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    fontFamily: FontFamily = FontFamily.Default,
    color : Color = MaterialTheme.colorScheme.primary,
    overflow :TextOverflow = TextOverflow.Visible,
    modifier : Modifier = Modifier,
) {
    Text(
        text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        color = color,
        modifier = modifier,
        textAlign = textAlign,
        overflow = overflow
    )
}