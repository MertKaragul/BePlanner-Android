package com.mertkaragul.beplanner.View.Elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme

@Composable
fun BePSingleShowDialog(
    title : String,
    text : String,
    confirmButton : () -> Unit
) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        confirmButton = {
            BePButton(text = "Ok", onClick = confirmButton)
        },

        title = {
            BePText(text = title)
        },
        text = {
            BePText(text = text)
        }
    )
}


@Preview
@Composable
fun PreviewBePAlertDialog() {
    BePlannerTheme {
        BePSingleShowDialog("Alert!", "Test Alert Dialog"){

        }
    }
}