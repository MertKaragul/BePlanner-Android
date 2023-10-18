package com.mertkaragul.beplanner.View.Page.Home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.mertkaragul.beplanner.R
import com.mertkaragul.beplanner.View.Elements.BePButton
import com.mertkaragul.beplanner.View.Elements.BePDialog
import com.mertkaragul.beplanner.View.Elements.BePIconButton
import com.mertkaragul.beplanner.Viewmodel.HomeViewModel

@Composable
fun HomeFloatAction(
    viewmodel : HomeViewModel
) {
    var clearAllDialog by remember {
        mutableStateOf(false)
    }
    val height = LocalConfiguration.current.screenHeightDp
    val width = LocalConfiguration.current.screenWidthDp
    Column(modifier = Modifier.padding(10.dp)) {
        BePIconButton(icon = R.drawable.baseline_clear_24,
            onClick = { clearAllDialog = true },
            modifier = Modifier
                .height((height * .9).dp)
                .width((width * .12).dp)
                .clip(RoundedCornerShape(100.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(5.dp)
        )

        BePIconButton(icon = R.drawable.baseline_refresh_24,
            onClick = { viewmodel.getPlans() },
            modifier = androidx.compose.ui.Modifier
                .height((height * .9).dp)
                .width((width * .12).dp)
                .clip(RoundedCornerShape(100.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(5.dp)
        )
    }

    AnimatedVisibility(visible = clearAllDialog) {
        BePDialog(
            title = "Are you sure?",
            description = "All reminded plans will be deleted",
            onDismissRequest = {
                clearAllDialog = false
            },
            confirm = {
                BePButton(text = "Ok", onClick = {
                    clearAllDialog = false
                    viewmodel.clearAllRemindedPlans()
                    viewmodel.getPlans()
                })
            },
            dismiss = {
                BePButton(text = "Cancel", onClick = { clearAllDialog = false })
            }
        )
    }
}