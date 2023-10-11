package com.mertkaragul.beplanner.View.Elements

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun BePDefaultHeightSpacer() {
    val deviceHeight = LocalConfiguration.current.screenHeightDp
    Spacer(modifier = Modifier.height((deviceHeight * 0.005).dp))
}

@Composable
fun BePDefaultWidthSpacer() {
    val deviceWidth = LocalConfiguration.current.screenWidthDp
    Spacer(modifier = Modifier.width((deviceWidth * 0.005).dp))
}
@Composable
fun BePDefaultWidthAndHeightSpacer() {
    val deviceWidth = LocalConfiguration.current.screenWidthDp
    val deviceHeight = LocalConfiguration.current.screenHeightDp
    Spacer(modifier = Modifier.size(((deviceHeight + deviceWidth) * .005).dp))
}