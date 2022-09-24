package com.egiwon.notificationpermissiontest.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.egiwon.notificationpermissiontest.MainActivity
import com.egiwon.notificationpermissiontest.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFireBaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
    }

    override fun onMessageReceived(message: RemoteMessage) {

        if (message.notification != null) {
            sendNotification(message.notification ?: return)
        }
    }

    private fun sendNotification(notification: RemoteMessage.Notification) {
        val messageBody: String? = notification.body
        val messageTitle: String? = notification.title

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(messageTitle)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, builder.build())
    }


    companion object {
        const val CHANNEL_ID = "fcm"
    }
}
