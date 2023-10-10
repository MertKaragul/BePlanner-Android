package com.mertkaragul.beplanner.View.Page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme

@Composable
fun Planner() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlanner() {
    BePlannerTheme {
        Planner()
    }
}