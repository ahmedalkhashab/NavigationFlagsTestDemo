package com.example.navigationdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class ActivityA : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Activity A"
        // Add to ActivityTracker
        ActivityTracker.addActivity(this)
        setContent {
            ActivityScreen("A") {
                startActivity(Intent(this, ActivityB::class.java))
            }
        }
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
        Log.d("ActivityTracker: Activity A", "Current Activity Back Stack: $activityNames")
    }

}