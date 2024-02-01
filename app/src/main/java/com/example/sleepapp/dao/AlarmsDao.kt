package com.example.sleepapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.sleepapp.alarmsscreen.Alarm
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmsDao {
    @Upsert
    suspend fun upsertAlarm(alarmToAdd: Alarm)
    @Delete
    suspend fun deleteAlarm(alarmToDelete: Alarm)
    @Query("SELECT * FROM alarms")
    fun getAllAlarms(): Flow<List<Alarm>>
    @Query("SELECT * FROM alarms WHERE id = :alarmId")
    fun getAlarmById(alarmId: Int): Flow<Alarm?>
}