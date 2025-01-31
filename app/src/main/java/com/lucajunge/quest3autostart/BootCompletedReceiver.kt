package com.lucajunge.quest3autostart

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log

class BootCompletedReceiver : BroadcastReceiver() {

    // TODO: customize this to specify which app to start
    val PACKAGE_TO_START: String = "com.lucajunge.xrhome"
    val CLASS_TO_START: String = "com.godot.game.GodotApp"

    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {

            Log.v("BootReceiver", "Boot Completed")

            // We are launching this activity / app before we autostart the needed app
            val launchIntent = context.packageManager.getLaunchIntentForPackage("com.lucajunge.quest3autostart")
            launchIntent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(launchIntent)

            // We delay the startup code a bit to make sure the OS booted properly
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                val intent = Intent()
                intent.setClassName(PACKAGE_TO_START, CLASS_TO_START)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                if (intent != null) {
                    // TODO: Make sure the app really starts. The 5000ms delay could be too low
                    Log.v("BootReceiver", "Starting activity")
                    context.startActivity(intent)
                } else {
                    Log.v("BootReceiver", "Intent not found")
                }
            }, 5000) // 5000ms
        }
    }
}
