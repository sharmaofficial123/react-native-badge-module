package com.reactnativebadgemodule

import com.facebook.react.TurboReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider
import com.facebook.react.uimanager.ViewManager

class BadgePackage : TurboReactPackage() {

    override fun getModule(name: String, reactContext: ReactApplicationContext): NativeModule? {
        return if (name == BadgeModule.NAME) BadgeModule(reactContext) else null
    }

    override fun getReactModuleInfoProvider() = ReactModuleInfoProvider {
        mapOf(
            BadgeModule.NAME to ReactModuleInfo(
                BadgeModule.NAME,
                BadgeModule.NAME,
                false,
                false,
                false,
                true  // isTurboModule
            )
        )
    }
}