package app.test.aura.data.notifications

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import app.test.aura.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val CHANNEL_ID = "boot_notification_channel"
private const val BOOT_UPDATE_NOTIFICATION_ID = 189212
const val INTENT_ACTION_NOTIFICATION_DELETE = "app.test.aura.data.notifications.INTENT_ACTION_NOTIFICATION_DELETE"

class NotificationDispatcher @Inject constructor(
    @ApplicationContext
    private val context: Context
) {
    fun tryPostNotification(payload: String) {
        createNotificationChannel()

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            postNotificationInternal(payload)
            return
        }

        if (context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            postNotificationInternal(payload)
        }
    }

    @SuppressLint("MissingPermission")
    private fun postNotificationInternal(payload: String) {
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Boot update")
            .setContentText(payload)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setDeleteIntent(
                PendingIntent.getBroadcast(
                    context,
                    19231,
                    Intent(INTENT_ACTION_NOTIFICATION_DELETE),
                    FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            )
            .build()

        NotificationManagerCompat
            .from(context)
            .notify(BOOT_UPDATE_NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Boot App Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)

            NotificationManagerCompat.from(context).createNotificationChannel(channel)
        }
    }
}