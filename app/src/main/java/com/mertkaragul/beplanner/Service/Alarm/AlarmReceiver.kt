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
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mertkaragul.beplanner.R
import com.mertkaragul.beplanner.Service.Database.DatabaseSetup
import com.mertkaragul.beplanner.Service.Notification.NotificationUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AlarmReceiver : BroadcastReceiver() {
    private val setupDatabase = DatabaseSetup()
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return
        val message = intent?.getStringExtra("EXTRA_MESSAGE") ?: "I don't know but any plan triggered"

        println("ALARM RECEIVER WORKING")

        createChannel(context)
        val notification = NotificationCompat.Builder(context, NotificationUtil.NOTIFICATION_ID.toString())
            .setSmallIcon(R.drawable.baseline_home_24)
            .setContentTitle(context.resources.getText(R.string.app_name))
            .setContentText(message)
            .build()
        if(ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED){
            with(NotificationManagerCompat.from(context)) {
                notify(NotificationUtil.NOTIFICATION_ID, notification)
            }
        }
        changePlanStatus(message,context)
    }

    /** If first time sending a notification create a notification channel, this channel required  **/
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

    /** Notification sent change plan status in database **/
    private fun changePlanStatus(planName : String, context : Context){
        val database = setupDatabase.setupDatabaseCallback(context)
        CoroutineScope(Dispatchers.IO).launch {
            val findPlan = CoroutineScope(Dispatchers.IO).async {
                return@async database.plannerDAO().getByName(planName)
            }.await()
            findPlan.status = true
            database.plannerDAO().update(findPlan)
        }
        database.close()
    }
}