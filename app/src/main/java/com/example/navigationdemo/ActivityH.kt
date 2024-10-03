package com.example.navigationdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class ActivityH : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Activity H"
        // Add to ActivityTracker
        ActivityTracker.addActivity(this)
        val targetActivity = intent.getStringExtra("target_activity")
        setContent {
            ActivityHContent(className = targetActivity?.split(".")?.last() ?: "C") { inputData ->
                // Convert the class name string back to a Class object
                val targetClass = if (targetActivity == null) ActivityC::class.java
                else {
                    try { Class.forName(targetActivity) }
                    catch (e: ClassNotFoundException) { ActivityC::class.java }
                }
                val intent = Intent(this@ActivityH, targetClass).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    putExtra("data_from_H", inputData)
                }
                startActivity(intent)
                finish()
            }
        }
        printActivityBackStack()
    }

    override fun onResume() {
        super.onResume()
        printActivityBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Remove from ActivityTracker
        ActivityTracker.removeActivity(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        // Print the activity back stack
        printActivityBackStack()
    }

    private fun printActivityBackStack() {
        val activityNames = ActivityTracker.getActivities().map { it::class.java.simpleName }
        Log.d("ActivityTracker: Activity H", "Current Activity Back Stack: $activityNames")
    }

}

