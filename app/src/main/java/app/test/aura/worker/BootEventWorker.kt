package app.test.aura.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import app.test.aura.data.notifications.NotificationDispatcher
import app.test.aura.domain.BootRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit

@HiltWorker
class BootEventWorker @AssistedInject constructor(
    private val bootRepository: BootRepository,
    private val notificationDispatcher: NotificationDispatcher,
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(
    context,
    workerParams
) {
    override suspend fun doWork(): Result {
        notificationDispatcher.tryPostNotification("TODO: Implement")
        bootRepository.saveBootTime(System.currentTimeMillis())
        return Result.success()
    }

    companion object {
        private const val REPEAT_INTERVAL_MINUTES = 15L

        fun launch(context: Context) {
            val workRequest = PeriodicWorkRequestBuilder<BootEventWorker>(
                repeatInterval = REPEAT_INTERVAL_MINUTES,
                repeatIntervalTimeUnit = TimeUnit.MINUTES
            ).build()

            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(
                    "device_boot_tracker",
                    ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
                    workRequest
                )
        }
    }
}