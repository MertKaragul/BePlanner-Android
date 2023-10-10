package com.mertkaragul.beplanner.View.Page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme

@Composable
fun Home() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Hello")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome(){
    BePlannerTheme {
        Home()
    }
}