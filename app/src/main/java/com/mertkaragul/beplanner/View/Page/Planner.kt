package com.mertkaragul.beplanner.View.Page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.mertkaragul.beplanner.View.Elements.BePDefaultHeightSpacer
import com.mertkaragul.beplanner.View.Elements.BePText
import com.mertkaragul.beplanner.View.Elements.BePTextField
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme

@Composable
fun Planner() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var text by remember { mutableStateOf("") }

        BePText(text = "Let's take a plan", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        BePDefaultHeightSpacer()
        BePTextField(value = text, onValueChange = { text = it } , placeholder = "Plan name")

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPlanner() {
    BePlannerTheme {
        Planner()
    }
}