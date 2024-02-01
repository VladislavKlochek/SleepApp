package com.example.sleepapp.dao

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.sleepapp.App
import com.example.sleepapp.alarmsscreen.Alarm
import kotlinx.coroutines.launch
import java.io.Serializable


class AlarmsViewModel(
    val database: Database
) : ViewModel(), Serializable {
    val itemsList = database.alarmsDao.getAllAlarms()
    val alarm: Alarm? = null

    fun insertItem(alarm: Alarm) = viewModelScope.launch {
        itemsList.collect { alarms ->
            val hasMatchingDate = alarms.any { alarm1 ->
                alarm1.hours == alarm.hours && alarm1.minutes == alarm.minutes
            }

            if (!hasMatchingDate) {
                database.alarmsDao.upsertAlarm(alarm)
            }
        }
    }
    fun deleteAlarm(alarm: Alarm) = viewModelScope.launch {
        database.alarmsDao.deleteAlarm(alarm)
    }
    fun getAlarmById(alarm: Alarm) = viewModelScope.launch {
        database.alarmsDao.getAlarmById(alarm.id)
    }
    fun updateSwitchState(alarm: Alarm) = viewModelScope.launch{
        database.alarmsDao.upsertAlarm(alarm)
    }
    companion object{
        val factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return AlarmsViewModel(database) as T
            }
        }
    }
}