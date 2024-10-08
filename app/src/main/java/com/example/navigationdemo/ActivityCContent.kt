package com.example.navigationdemo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
fun ActivityCContent(
    number: Int,
    dataFromH: String,
    onIncrementNumberClick: () -> Unit,
    onNavigation: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Activity C") })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Number: $number",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { onIncrementNumberClick() }) {
                    Text(text = "Increment Number")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { onNavigation() }) {
                    Text(text = "Go to Activity D")
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (dataFromH.isNotEmpty()) {
                    Text(
                        text = "Data from Activity H: $dataFromH",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}