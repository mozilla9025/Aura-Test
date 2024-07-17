package app.test.aura.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import app.test.aura.data.notifications.INTENT_ACTION_NOTIFICATION_DELETE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationDismissReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action != INTENT_ACTION_NOTIFICATION_DELETE) return

        // TODO : Implement notification delete action handling
    }
}