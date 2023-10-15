package com.mertkaragul.beplanner.Service.Alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.mertkaragul.beplanner.IAlarmScheduler
import com.mertkaragul.beplanner.Model.AlarmModel.AlarmItem
import java.time.ZoneId

class AlarmService : IAlarmScheduler {
    override fun schedule(context : Context,item: AlarmItem) {
        val getAlarmService = context.getSystemService(AlarmManager::class.java)
        val intent = Intent(context , AlarmReceiver::class.java).apply {
            putExtra("EXTRA_MESSAGE" , item.message)
        }
        val alarmReceiver = PendingIntent.getBroadcast(context, item.hashCode() ,intent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)

        getAlarmService.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            item.time,
            alarmReceiver
        )
    }

    override fun cancel(context : Context,item: AlarmItem) {
        val getAlarmService = context.getSystemService(AlarmManager::class.java)
        val intent = Intent(context , AlarmReceiver::class.java)
        getAlarmService.cancel {
            PendingIntent.getBroadcast(context, item.hashCode() ,intent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)
        }
    }
}