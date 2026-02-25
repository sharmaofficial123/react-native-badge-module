package com.reactnativebadgemodule

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import me.leolin.shortcutbadger.ShortcutBadger

class BadgeModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
  private val prefs: SharedPreferences =
    reactContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

  override fun getName(): String {
    return NAME
  }

  @ReactMethod
  fun setBadgeCount(count: Int) {
    try {
      ShortcutBadger.applyCount(reactApplicationContext, count)
      prefs.edit().putInt(KEY_BADGE_COUNT, count).apply()
      Log.d(NAME, "Badge count set to $count")
    } catch (e: Exception) {
      Log.e(NAME, "Error setting badge count", e)
    }
  }

  @ReactMethod
  fun clearBadge() {
    try {
      ShortcutBadger.removeCount(reactApplicationContext)
      prefs.edit().putInt(KEY_BADGE_COUNT, 0).apply()
      Log.d(NAME, "Badge cleared")
    } catch (e: Exception) {
      Log.e(NAME, "Error clearing badge", e)
    }
  }

  @ReactMethod
  fun getBadgeCount(promise: Promise) {
    try {
      val count = prefs.getInt(KEY_BADGE_COUNT, 0)
      promise.resolve(count)
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
