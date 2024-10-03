package com.example.navigationdemo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityScreen(
    activityName: String,
    dataFromH: String? = null,
    isJourneyInitialActivity: Boolean = false,
    onTargetClick: (() -> Unit)? = null,
    onNextClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Activity $activityName") })
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Button(onClick = onNextClick) {
                    Text(text = "Go to Activity ${nextActivityName(activityName)}")
                }
                Spacer(modifier = Modifier.height(144.dp))
                if(isJourneyInitialActivity){
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                        onClick = { onTargetClick?.invoke() }) {
                        Text(text = "Your Journey Initial Activity is $activityName")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (dataFromH.isNullOrBlank().not()) {
                    Text(
                        text = "Data from Activity H: $dataFromH",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

fun nextActivityName(current: String): String = when (current) {
    "A" -> "B"
    "B" -> "C"
    "C" -> "D"
    "D" -> "E"
    "E" -> "F"
    "F" -> "G"
    "G" -> "H"
    else -> "A"
}