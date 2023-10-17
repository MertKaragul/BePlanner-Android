package com.mertkaragul.beplanner.View.Elements

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.mertkaragul.beplanner.R
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme

@Composable
fun BePButton(
    text : String,
    onClick : () -> Unit,
    buttonColors : ButtonColors = ButtonDefaults.buttonColors(),
    fontSize : TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    fontFamily: FontFamily = FontFamily.Default,
    textColor : Color = MaterialTheme.colorScheme.onPrimary,
    modifier : Modifier = Modifier,
    textModifier : Modifier = Modifier
) {
    Button(onClick = onClick, modifier = modifier , colors = buttonColors) {
        BePText(
            text = text,
            modifier = textModifier,
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            color = textColor
        )
    }
}


@Composable
fun BePTextButton(
    text : String,
    onClick : () -> Unit,
    buttonColors : ButtonColors = ButtonDefaults.buttonColors(),
    fontSize : TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    fontFamily: FontFamily = FontFamily.Default,
    textColor : Color = MaterialTheme.colorScheme.onPrimary,
    modifier : Modifier = Modifier,
    textModifier : Modifier = Modifier
) {
   TextButton(
       onClick = onClick,
       modifier = modifier,
   ){
       BePText(
           text = text,
           modifier = textModifier,
           fontSize = fontSize,
           fontWeight = fontWeight,
           fontFamily = fontFamily,
           color = textColor
       )
   }
}

@Composable
fun BePIconButton(
    icon : Int,
    onClick : () -> Unit,
    tint : Color = MaterialTheme.colorScheme.primary,
    modifier : Modifier = Modifier
) {
   IconButton(onClick = onClick) {
       Icon(
          painter = painterResource(id = icon),
           modifier = modifier,
           contentDescription = "",
           tint = tint
       )
   }
}

@Preview
@Composable
fun PreviewBepButton() {
    BePlannerTheme {
        BePIconButton(R.drawable.baseline_home_24, { println("Hello world") })
    }
}