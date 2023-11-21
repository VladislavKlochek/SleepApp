package com.example.sleepapp.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class Converters {
    @TypeConverter
    fun fromDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun toDate(date: Date?): Long? {
        return date?.time
    }
    @TypeConverter
    fun fromStringArray(value: String?): Array<String>? {
        return value?.let {
            val type = object : TypeToken<Array<String>>() {}.type
            Gson().fromJson(it, type)
        }
    }

    @TypeConverter
    fun toStringArray(value: Array<String>?): String? {
        return value?.let { Gson().toJson(it) }
    }
}