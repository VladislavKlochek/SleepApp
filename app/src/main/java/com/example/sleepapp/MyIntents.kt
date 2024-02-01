package com.example.sleepapp

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.sleepapp.activities.MainActivity

class MyIntents(private val context: Context) {

    fun getAlarmInfoPendingIntent(): PendingIntent {
        val alarmInfoIntent = Intent(context, MainActivity::class.java)
        alarmInfoIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        return PendingIntent.getActivity(context, 0, alarmInfoIntent, PendingIntent.FLAG_IMMUTABLE)
    }

    fun getAlarmActionPendingIntent(): PendingIntent {
        val intent = Intent(context, AlarmActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        return PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_IMMUTABLE)
    }
}
