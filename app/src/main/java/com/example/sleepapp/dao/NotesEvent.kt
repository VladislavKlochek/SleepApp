package com.example.sleepapp.dao
import com.example.sleepapp.notesscreen.note
import java.time.LocalDate

sealed interface NotesEvent{
    object SaveNote: NotesEvent
    data class SetName(val name: String): NotesEvent
    data class SetId(val id: Int): NotesEvent
    data class SetMainText(val mainText: String): NotesEvent
    data class SetDate(val date: LocalDate): NotesEvent
    data class SetIsAffectedByAlcohol(val isAffectedByAlcohol: Boolean): NotesEvent
    data class SetIsAffectedByCoffee(val isAffectedByCoffee: Boolean): NotesEvent
    data class SetTags(val tags: Array<String>): NotesEvent
    data class DeleteNote(val note: note): NotesEvent

}