package com.mertkaragul.beplanner.Service.Alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.mertkaragul.beplanner.R
import com.mertkaragul.beplanner.Service.Notification.NotificationUtil

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return
        val message = intent?.getStringExtra("EXTRA_MESSAGE")

        println("i catch message : $message")

        createChannel(context)
        val notification = NotificationCompat.Builder(context, NotificationUtil.NOTIFICATION_ID.toString())
            .setSmallIcon(R.drawable.baseline_home_24)
            .setContentTitle(context.resources.getText(R.string.app_name))
            .setContentText(message.toString())
            .build()


        if(ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED){
            with(NotificationManagerCompat.from(context)) {
                notify(NotificationUtil.NOTIFICATION_ID, notification)
            }
        }
    }

    private fun createChannel(context : Context){
        val name = R.string.app_name
        val descriptionText = "BePlanner alarm notification"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(NotificationUtil.NOTIFICATION_ID.toString(), name.toString(), importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}