package com.example.sleepapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.sleepapp.notesscreen.note
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface NotesDao {
    @Upsert
    suspend fun upsertNote(noteToAdd: note)
    @Delete
    suspend fun deleteNote(noteToDelete: note)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<note>>
    @Query("SELECT * FROM notes WHERE text LIKE '%' || :tag || '%'")
    fun getNotesWithTag(tag: String): Flow<List<note>>

    @Query("SELECT * FROM notes WHERE date = :desiredDate")
    fun getNotesByDate(desiredDate: LocalDate): Flow<List<note>>

    @Query("SELECT * FROM notes WHERE id = :noteId")
    fun getNoteById(noteId: Long): Flow<note?>
}