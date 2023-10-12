package com.mertkaragul.beplanner.View.Elements

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.inputmethod.InputMethodSubtype
import com.mertkaragul.beplanner.Model.Picker.DatePickerModel
import com.mertkaragul.beplanner.Model.Picker.TimePickerModel
import java.util.Calendar

class BePPicker {
    fun timePicker(context : Context, calendar : Calendar, timePickerModel: (TimePickerModel) -> Unit) {
        TimePickerDialog(
            context,
            {_, mHour : Int, mMinute: Int ->
                timePickerModel(TimePickerModel(mHour, mMinute))
            }, calendar.get(Calendar.HOUR_OF_DAY) , calendar.get(Calendar.MINUTE), false
        ).show()
    }

    fun dateTimePicker(context : Context, calendar : Calendar) : DatePickerModel{
        var selectedYear = 0
        var selectedMonth = 0
        var selectedDay = 0
        DatePickerDialog(
            context,
            {_, year:Int,month : Int, day : Int ->
                selectedYear = year
                selectedMonth = month
                selectedDay = day
            },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)
        ).show()

        return DatePickerModel(selectedYear,selectedMonth,selectedDay)
    }
}