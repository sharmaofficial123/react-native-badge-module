package com.reactnativebadgemodule

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.facebook.react.bridge.*
import com.facebook.react.module.annotations.ReactModule
import me.leolin.shortcutbadger.ShortcutBadger

@ReactModule(name = BadgeModule.NAME)
class BadgeModule(reactContext: ReactApplicationContext) :
    NativeBadgeModuleSpec(reactContext) {

    private val prefs: SharedPreferences =
        reactContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun getName() = NAME

    override fun setBadgeCount(count: Double) {
        try {
            ShortcutBadger.applyCount(reactApplicationContext, count.toInt())
            prefs.edit().putInt(KEY_BADGE_COUNT, count.toInt()).apply()
        } catch (e: Exception) {
            Log.e(NAME, "Error setting badge count", e)
        }
    }

    override fun clearBadge() {
        try {
            ShortcutBadger.removeCount(reactApplicationContext)
            prefs.edit().putInt(KEY_BADGE_COUNT, 0).apply()
        } catch (e: Exception) {
            Log.e(NAME, "Error clearing badge", e)
        }
    }

    override fun getBadgeCount(promise: Promise) {
        try {
            promise.resolve(prefs.getInt(KEY_BADGE_COUNT, 0).toDouble())
        } catch (e: Exception) {
            promise.reject("E_BADGE", "Error getting badge count", e)
        }
    }

    companion object {
        const val NAME = "BadgeModule"
        private const val PREFS_NAME = "BadgePrefs"
        private const val KEY_BADGE_COUNT = "badge_count"
    }
}