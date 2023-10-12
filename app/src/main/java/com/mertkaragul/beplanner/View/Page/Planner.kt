package com.mertkaragul.beplanner.View.Page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.mertkaragul.beplanner.Model.Picker.DatePickerModel
import com.mertkaragul.beplanner.Model.Picker.TimePickerModel
import com.mertkaragul.beplanner.View.Elements.BePButton
import com.mertkaragul.beplanner.View.Elements.BePDefaultHeightSpacer
import com.mertkaragul.beplanner.View.Elements.BePPicker
import com.mertkaragul.beplanner.View.Elements.BePText
import com.mertkaragul.beplanner.View.Elements.BePTextField
import com.mertkaragul.beplanner.ui.theme.BePlannerTheme
import java.util.Calendar

@Composable
fun Planner(
    picker : BePPicker = BePPicker()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val context = LocalContext.current
        val calendar = Calendar.getInstance()
        var text by remember { mutableStateOf("") }
        var timerModel by remember { mutableStateOf(TimePickerModel(0,0,0))  }
        var dateModel by remember { mutableStateOf(DatePickerModel(0,0,0))  }

        BePText(text = "Let's take a plan", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        BePDefaultHeightSpacer()
        BePTextField(value = text, onValueChange = { text = it } , placeholder = "Plan name")
        BePDefaultHeightSpacer()
        if(timerModel.hour != 0 && timerModel.minute != 0 ) {
            BePTextField(value = "${timerModel.hour}:${timerModel.minute}", onValueChange = {}, placeholder = "Selected time" ,enabled = false )
        }
        BePDefaultHeightSpacer()
        BePButton(text = "Select Time", onClick = {
            picker.timePicker(context, calendar){
                timerModel = it
            }
        })
        BePDefaultHeightSpacer()
        BePButton(text = "Select Date", onClick = {
            dateModel =  picker.dateTimePicker(context,calendar)
        })


        BePButton(text = "Create plan", onClick = {  })


    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPlanner() {
    BePlannerTheme {
        Planner()
    }
}