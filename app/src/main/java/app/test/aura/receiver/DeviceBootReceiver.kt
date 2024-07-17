package app.test.aura.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import app.test.aura.worker.BootEventWorker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeviceBootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action != Intent.ACTION_BOOT_COMPLETED) return

        BootEventWorker.launch(context)
    }
}