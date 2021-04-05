package com.example.accessibilitys

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.ACTION_DOWN
import android.view.KeyEvent.ACTION_UP
import android.view.accessibility.AccessibilityEvent

import com.zello.sdk.Zello;


class MyAccessibilityService : AccessibilityService() {
    private val TAG = "test"

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.i(TAG, "Service connected")
    }
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "on create")
        Zello.getInstance().configure(this);

        Log.d(TAG, "zello configured on create")
    }

    override fun onKeyEvent(event: KeyEvent): Boolean {
        Log.d(TAG, "Key pressed via accessibility is: " + event.keyCode + ", " + event.action.toString())
        Log.d(TAG, "Key pressed via accessibility is: " + event.toString())
        // Ignore non-external keypresses and process them normally.
        if (! event.device.isExternal) {
            return super.onKeyEvent(event)
        }
        if (event.action == ACTION_UP) {
            Zello.getInstance().endMessage()
        } else if (event.action == ACTION_DOWN) {
            Zello.getInstance().beginMessage()
        }
        return true
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {}
    override fun onInterrupt() {}
}