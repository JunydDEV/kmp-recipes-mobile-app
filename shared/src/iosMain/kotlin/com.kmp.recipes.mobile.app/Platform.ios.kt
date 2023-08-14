package com.kmp.recipes.mobile.app

import androidx.compose.ui.unit.dp
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual val safeAreaPadding = 50.dp