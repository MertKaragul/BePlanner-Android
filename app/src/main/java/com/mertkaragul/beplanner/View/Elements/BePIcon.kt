package com.mertkaragul.beplanner.View.Elements

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mertkaragul.beplanner.R
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme

@Composable
fun BePIcon(
    icon : Int,
    color : Color = MaterialTheme.colorScheme.primary,
    modifier : Modifier = Modifier
) {
    Icon(
        painter = painterResource(id = icon),
        tint = color,
        contentDescription = "",
        modifier = modifier,
    )
}

@Preview
@Composable
fun PreviewBePIcon() {
    BePlannerTheme {
        BePIcon(icon = R.drawable.baseline_home_24)
    }
}