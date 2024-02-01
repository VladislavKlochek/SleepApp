package com.example.sleepapp.alarmsscreen

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "alarms")
data class Alarm(
    var hours : Int,
    var minutes: Int,
    var isSet: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
) : Serializable
