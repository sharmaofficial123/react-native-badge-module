package com.reactnativebadgemodule

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.facebook.react.bridge.*

class BadgeModule(private val reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    override fun getName() = "BadgeModule"

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "badge_channel",
                "Badge Notifications",
                NotificationManager.IMPORTANCE_MIN
            ).apply { setShowBadge(true) }
            val manager = reactContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    @ReactMethod
    fun setBadgeCount(count: Int) {
        createChannel()
        val manager = NotificationManagerCompat.from(reactContext)
        if (count == 0) { manager.cancel(1); return }
        val notification = NotificationCompat.Builder(reactContext, "badge_channel")
            .setContentTitle("$count new updates")
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .setNumber(count)
            .setSilent(true)
            .build()
        manager.notify(1, notification)
    }

    @ReactMethod
    fun clearBadge() {
        NotificationManagerCompat.from(reactContext).cancel(1)
    }

    @ReactMethod
    fun getBadgeCount(promise: Promise) {
        promise.resolve(0)
    }
}