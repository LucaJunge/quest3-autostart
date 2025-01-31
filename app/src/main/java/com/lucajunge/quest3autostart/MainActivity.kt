package com.lucajunge.quest3autostart

import android.app.admin.DevicePolicyManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lucajunge.quest3autostart.ui.theme.Quest3AutostartTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var adminMessage: String = "The device owner state is unknown"
        var currentStartupApp: String = "com.lucajunge.xrhome"

        if (savedInstanceState == null) {
            val manager = getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager
            adminMessage = if (manager.isDeviceOwnerApp(applicationContext.packageName)) {
                "App is device owner"
            } else {
                "App is not device owner"
            }
        }

        Log.v("com.lucajunge.quest3autostart", adminMessage)

        enableEdgeToEdge()
        setContent {
            Quest3AutostartTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                        Text(text= "Quest 3 Autostart", textAlign = TextAlign.Center, style = MaterialTheme.typography.displayMedium)
                        Text(
                            text = adminMessage,
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(innerPadding)
                        )
                        Text(text = "Current startup app: " + currentStartupApp, textAlign = TextAlign.Start, style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(innerPadding))
                        Spacer(modifier = Modifier.height(16.dp))
                        MyButton(applicationContext)
                    }
                }
            }
        }
    }
}

@Composable
fun MyButton(context: Context) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                // Define the package name of the app you want to open
                val packageName: String = "com.lucajunge.xrhome"  // Change this to the desired app's package name
                val className: String = "com.godot.game.GodotApp"
                val intent = Intent()
                intent.setClassName(packageName, className)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                if (intent != null) {
                    context.startActivity(intent)
                } else {
                    Log.v("com.lucajunge.quest3autostart", "Intent could not be found")
                }
            }) {
            Text("Click me to start an app")
        }
}