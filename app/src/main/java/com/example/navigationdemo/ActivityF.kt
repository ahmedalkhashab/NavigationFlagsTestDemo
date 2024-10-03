package com.example.navigationdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf

class ActivityF : ComponentActivity() {

    private var dataFromH = mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Activity F"
        // Add to ActivityTracker
        ActivityTracker.addActivity(this)
        val targetActivity = intent.getStringExtra("target_activity")
        setContent {
            val nextClassName = ActivityG::class.java.name
            ActivityScreen(
                activityName = "F",
                dataFromH = dataFromH.value,
                onTargetClick = { startNextScreen(nextClassName, ActivityF::class.java.name) },
                onNextClick = { startNextScreen(nextClassName, targetActivity) }
            )
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
        // Retrieve the data sent from Activity H
        val dataFromH = intent.getStringExtra("data_from_H")
        if (dataFromH != null) this.dataFromH.value = dataFromH
        // Print the activity back stack
        printActivityBackStack()
    }

    private fun printActivityBackStack() {
        val activityNames = ActivityTracker.getActivities().map { it::class.java.simpleName }
        Log.d("ActivityTracker: Activity F", "Current Activity Back Stack: $activityNames")
    }

}