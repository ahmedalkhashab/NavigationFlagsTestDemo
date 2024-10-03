package com.example.navigationdemo

import android.content.Context
import android.content.Intent

fun Context.startNextScreen(nextClassName: String, journeyInitialClassName: String?) {
    startActivity(Intent().apply {
        setClassName(this@startNextScreen, nextClassName)
        putExtra("target_activity", journeyInitialClassName) }
    )
}