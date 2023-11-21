package com.example.sleepapp.dao

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.sleepapp.App
import com.example.sleepapp.notesscreen.note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.Serializable


class NotesViewModel(
    val database: Database
) : ViewModel(), Serializable {
    val itemsList = database.notesDao.getAllNotes()

    private val _state = MutableStateFlow(NotesState())
    var state = _state
    companion object{
        val factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).database
                return NotesViewModel(database) as T
            }
        }
    }
/*    fun getNoteById(noteId: Long): note? {
        return database.notesDao.getNoteById(noteId)
    }*/

    fun onEvent(notesEvent: NotesEvent){
        when(notesEvent){
            is NotesEvent.SetId -> {_state.update { it.copy(
                id = notesEvent.id
            ) }}
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    database.notesDao.deleteNote(notesEvent.note)
                }
            }
            NotesEvent.HideActivity -> TODO()
            NotesEvent.SaveNote -> {
                val name = _state.value.name
                val mainText = _state.value.mainText
                val date = _state.value.date
                val isAffectedByAlcohol = _state.value.isAffectedByAlcohol
                val isAffectedByCoffee = _state.value.isAffectedByCoffee
                val tags = _state.value.tags

                val id = _state.value.id
                val note = if(id == -1){
                    note(date, name, mainText, tags, isAffectedByCoffee, isAffectedByAlcohol)
                } else{
                    note(date, name, mainText, tags, isAffectedByCoffee, isAffectedByAlcohol, id)
                }
                viewModelScope.launch {
                    database.notesDao.upsertNote(note)
                }
            }
            is NotesEvent.SetDate -> {
                _state.update { it.copy(
                    date = notesEvent.date
                ) }
            }
            is NotesEvent.SetIsAffectedByAlcohol -> {
                _state.update { it.copy(
                    isAffectedByAlcohol = notesEvent.isAffectedByAlcohol
                ) }
            }
            is NotesEvent.SetIsAffectedByCoffee -> {
                _state.update { it.copy(
                    isAffectedByCoffee = notesEvent.isAffectedByCoffee
                ) }
            }
            is NotesEvent.SetMainText -> {
                _state.update { it.copy(
                    mainText = notesEvent.mainText
                ) }
            }
            is NotesEvent.SetName -> {
                _state.update { it.copy(
                    name = notesEvent.name
                ) }
            }
            is NotesEvent.SetTags -> {
                _state.update { it.copy(
                    tags = notesEvent.tags
                ) }
            }
            NotesEvent.ShowActivity -> TODO()
        }
    }
}