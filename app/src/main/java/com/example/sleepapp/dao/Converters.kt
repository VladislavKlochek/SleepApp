package com.example.sleepapp.dao

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDate

class Converters {
    @TypeConverter
    fun fromDate(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(it) }
    }

    @TypeConverter
    fun toDate(date: LocalDate?): Long? {
        return date?.toEpochDay()
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