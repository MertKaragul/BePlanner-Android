package com.mertkaragul.beplanner

import android.content.Context
import com.mertkaragul.beplanner.Model.AlarmModel.AlarmItem

interface IAlarmScheduler {
    fun schedule(context: Context,item : AlarmItem)

    fun cancel(context: Context,item : AlarmItem)
}