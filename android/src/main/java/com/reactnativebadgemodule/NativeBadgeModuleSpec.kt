package com.reactnativebadgemodule

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

abstract class NativeBadgeModuleSpec(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    @ReactMethod abstract fun setBadgeCount(count: Double)
    @ReactMethod abstract fun clearBadge()
    @ReactMethod abstract fun getBadgeCount(promise: Promise)
}