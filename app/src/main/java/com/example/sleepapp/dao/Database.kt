package com.example.sleepapp.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sleepapp.alarmsscreen.Alarm
import com.example.sleepapp.notesscreen.note

@Database(
    entities = [note::class, Alarm::class],
    version = 4
)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase(){
    abstract val notesDao:NotesDao
    abstract val alarmsDao:AlarmsDao
    companion object {
        fun createDatabase(context: Context): com.example.sleepapp.dao.Database{
            return Room.databaseBuilder(
                context,
                com.example.sleepapp.dao.Database::class.java,
                "sleep_database"
            ).fallbackToDestructiveMigration().build()
        }
    }
}