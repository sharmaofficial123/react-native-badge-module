package com.reactnativebadgemodule

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.facebook.react.bridge.*
import com.facebook.react.module.annotations.ReactModule

@ReactModule(name = BadgeModule.NAME)
class BadgeModule(private val reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    override fun getName() = NAME

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "badge_channel",
                "Badge Notifications",
                NotificationManager.IMPORTANCE_MIN
            ).apply { setShowBadge(true) }
            val manager = reactContext.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    @ReactMethod
    fun setBadgeCount(count: Double) {
        createChannel()
        val manager = NotificationManagerCompat.from(reactContext)
        val intCount = count.toInt()
        if (intCount == 0) { manager.cancel(1); return }
        val notification = NotificationCompat.Builder(reactContext, "badge_channel")
            .setContentTitle("$intCount new updates")
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .setNumber(intCount)
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
        promise.resolve(0.0)
    }

    companion object {
        const val NAME = "BadgeModule"
    }
}