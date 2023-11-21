package com.example.sleepapp.notesscreen

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(tableName = "notes")
data class note(
    val date: Date?,
    val noteName: String?,
    val text: String?,
    val tags: Array<String>?,
    val coffee: Boolean,
    val alcohol: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
) : Serializable {


}
