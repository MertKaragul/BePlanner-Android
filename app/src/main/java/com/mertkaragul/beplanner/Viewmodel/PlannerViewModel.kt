package com.mertkaragul.beplanner.Viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertkaragul.beplanner.Model.AlarmModel.AlarmItem
import com.mertkaragul.beplanner.Model.DatabaseModel.DatabasePlanModel
import com.mertkaragul.beplanner.Service.Alarm.AlarmService
import com.mertkaragul.beplanner.Service.Database.DatabaseUtils.database
import kotlinx.coroutines.launch
import java.util.UUID

class PlannerViewModel : ViewModel() {
    private val alarmService = AlarmService()

    fun schedulePlan(context : Context, alarmItem: AlarmItem){
        alarmService.schedule(context, alarmItem)
        try {
            viewModelScope.launch {
                database?.plannerDAO()?.insert(DatabasePlanModel(Math.random().toInt(),alarmItem.message, time = alarmItem.time, false))
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}