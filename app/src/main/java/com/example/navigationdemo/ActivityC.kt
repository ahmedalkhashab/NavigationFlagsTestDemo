package com.example.navigationdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

class ActivityC : ComponentActivity() {

    private val viewModel: NumberViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Activity C"
        // Add to ActivityTracker
        ActivityTracker.addActivity(this)
        setContent {
            val number by viewModel.number.collectAsState()
            val dataFromH by viewModel.dataFromH.collectAsState()
            ActivityCContent(
                number = number,
                dataFromH = dataFromH,
                onIncrementNumberClick = viewModel::incrementNumber
            ) { startActivity(Intent(this@ActivityC, ActivityD::class.java)) }
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

    // Handle new intent when using FLAG_ACTIVITY_SINGLE_TOP
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        // Retrieve the data sent from Activity H
        val dataFromH = intent.getStringExtra("data_from_H")
        if (dataFromH != null) {
            // Update the ViewModel or state accordingly
            viewModel.updateDataFromH(dataFromH)
        }
        // Print the activity back stack
        printActivityBackStack()
    }

    private fun printActivityBackStack() {
        val activityNames = ActivityTracker.getActivities().map { it::class.java.simpleName }
        Log.d("ActivityTracker: Activity C", "Current Activity Back Stack: $activityNames")
    }
}

