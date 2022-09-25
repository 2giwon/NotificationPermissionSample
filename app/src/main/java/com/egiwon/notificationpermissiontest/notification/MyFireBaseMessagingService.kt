package com.egiwon.notificationpermissiontest.notification

import android.app.NotificationManager
import androidx.core.content.ContextCompat
import com.egiwon.notificationpermissiontest.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFireBaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
    }

    override fun onMessageReceived(message: RemoteMessage) {

        message.notification?.let {
            sendNotification(it.body ?: return@let)
        }
    }

    private fun sendNotification(messageBody: String) {

        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager
        notificationManager.sendNotification(messageBody, applicationContext)
    }

}
