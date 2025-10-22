package com.example.wordflux

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class WordFluxAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // 🧠 Handle text changes or view interactions here
    }

    override fun onInterrupt() {
        // ⚡️ Clean interruption handling
    }
}



