package com.mertkaragul.beplanner.Model.Picker

import android.app.TimePickerDialog.OnTimeSetListener

data class TimePickerModel(
    val hour : Int,
    val minute : Int,
    var second : Int = 0
)