package com.example.navigationdemo

import android.app.Activity

object ActivityTracker {

    private val activities = mutableListOf<Activity>()

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    fun getActivities(): List<Activity> {
        return activities.toList()
    }
}