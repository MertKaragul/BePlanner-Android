package com.mertkaragul.beplanner.View.Elements

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.mertkaragul.beplanner.R
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme

@Composable
fun BePDialog(
    title : String,
    description : String,
    confirm : @Composable () -> Unit,
    dismiss : @Composable () -> Unit,
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        dismissButton = dismiss,
        confirmButton = confirm,
        title = {
            BePText(text = title, fontWeight = FontWeight.Bold, fontSize = 25.sp)
        },
        text = {
            BePText(text = description)
        },
        onDismissRequest = onDismissRequest
    )
}

@Preview
@Composable
fun PreviewBePAlertDialog() {
    BePlannerTheme {
        BePDialog("Alert!", "Test Alert Dialog",
            confirm = {
                BePIconButton(icon = R.drawable.baseline_home_24, onClick = { /*TODO*/ })
            },
            dismiss = {
                BePIconButton(icon = R.drawable.baseline_clear_24, onClick = { /*TODO*/ })
            },
            onDismissRequest = {
                // User click back button or clicked any area
            }
        )
    }
}