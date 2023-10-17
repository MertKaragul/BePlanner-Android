package com.mertkaragul.beplanner.View.Page

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mertkaragul.beplanner.Model.AlarmModel.AlarmItem
import com.mertkaragul.beplanner.Model.Picker.DatePickerModel
import com.mertkaragul.beplanner.Model.Picker.TimePickerModel
import com.mertkaragul.beplanner.View.Elements.BePButton
import com.mertkaragul.beplanner.View.Elements.BePDefaultHeightSpacer
import com.mertkaragul.beplanner.View.Elements.BePPicker
import com.mertkaragul.beplanner.View.Elements.BePSingleShowDialog
import com.mertkaragul.beplanner.View.Elements.BePText
import com.mertkaragul.beplanner.View.Elements.BePTextField
import com.mertkaragul.beplanner.Viewmodel.PlannerViewModel
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme
import java.util.Calendar

@Composable
fun Planner(
    picker : BePPicker = BePPicker(),
    planner : PlannerViewModel = viewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val deviceWidth = LocalConfiguration.current.screenWidthDp
        val deviceHeight = LocalConfiguration.current.screenHeightDp
        val context = LocalContext.current
        val calendar = Calendar.getInstance()

        var showAlert by remember {
            mutableStateOf(false)
        }

        var alarmItem by remember { mutableStateOf<AlarmItem?>(null) }

        var planName by remember { mutableStateOf("") }
        var timerModel by remember { mutableStateOf(TimePickerModel(0,0,0))  }
        var dateModel by remember { mutableStateOf(DatePickerModel(0,0,0))  }

        Column(
            modifier = Modifier.width((deviceWidth * .8).dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround) {
                Column(modifier = Modifier
                    .size(((deviceHeight + deviceWidth) * .009).dp)
                    .clip(
                        RoundedCornerShape(100.dp)
                    )
                    .background(MaterialTheme.colorScheme.primary)) {}

                BePText(
                    text = "Let's take a plan",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Column(modifier = Modifier
                    .size(((deviceHeight + deviceWidth) * .009).dp)
                    .clip(
                        RoundedCornerShape(100.dp)
                    )
                    .background(MaterialTheme.colorScheme.primary)) {}
            }
            BePDefaultHeightSpacer()
            BePTextField(value = planName, onValueChange = { planName = it } , placeholder = "Plan name", modifier = Modifier.fillMaxWidth())
            BePDefaultHeightSpacer()
            BePTextField(value = "${timerModel.hour}:${timerModel.minute}",
                onValueChange = {},
                placeholder = if(timerModel.hour != 0 && timerModel.minute != 0) "Selected Time" else "Click and select time",
                enabled = false ,
                modifier = Modifier
                    .clickable {
                        picker.timePicker(context, calendar) {
                            timerModel = it
                        }
                    }
                    .fillMaxWidth())
            BePDefaultHeightSpacer()
            BePTextField(value = "${dateModel.dayOfMonth}.${dateModel.month}.${dateModel.year}",
                onValueChange = {},
                placeholder = if(dateModel.year != 0 && dateModel.month != 0 && dateModel.dayOfMonth != 0) "Selected Date" else "Click and select date",
                enabled = false,
                modifier = Modifier
                    .clickable {
                        picker.dateTimePicker(context, calendar) {
                            dateModel = it
                        }
                    }
                    .fillMaxWidth())

            BePDefaultHeightSpacer()
            BePButton(
                text = "Create plan",
                onClick = {
                    if (planName.isNotEmpty() && planName.isNotBlank()) {
                        if (dateModel.year == 0 || dateModel.month == 0 || dateModel.dayOfMonth == 0 || timerModel.hour == 0){
                            showAlert = true
                        }else{
                            calendar.set(dateModel.year,dateModel.month,dateModel.dayOfMonth,timerModel.hour,timerModel.minute,timerModel.second)
                            println(calendar.timeInMillis)
                            alarmItem = AlarmItem(message = planName, time = calendar.timeInMillis)
                            if (alarmItem != null){
                                planner.schedulePlan(context, alarmItem!!)
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth() ,
                buttonColors = ButtonDefaults.buttonColors( containerColor = MaterialTheme.colorScheme.primary)
            )
        }

        AnimatedVisibility(visible = showAlert, enter = fadeIn(), exit = fadeOut()) {
            BePSingleShowDialog(title = "Error", text = "Please select Time or calendar"){
                showAlert = false
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPlanner() {
    BePlannerTheme {
        Planner()
    }
}