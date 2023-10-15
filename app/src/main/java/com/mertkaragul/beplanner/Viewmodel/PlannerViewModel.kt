package com.mertkaragul.beplanner.Viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.mertkaragul.beplanner.Model.AlarmModel.AlarmItem
import com.mertkaragul.beplanner.Service.Alarm.AlarmService

class PlannerViewModel : ViewModel() {
    private val alarmService = AlarmService()

    fun schedulePlan(context : Context, alarmItem: AlarmItem){
        alarmService.schedule(context, alarmItem)
    }
}