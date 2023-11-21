package com.example.sleepapp.dao

import com.example.sleepapp.notesscreen.note
import java.util.Date

data class NotesState(
    var id: Int = -1,
    var notes:List<note> = emptyList(),
    var name: String? = "",
    var mainText: String? = "",
    var date: Date? = null,
    var isAffectedByAlcohol: Boolean = false,
    var isAffectedByCoffee: Boolean = false,
    var tags: Array<String>? = null,
    var isAddingNote: Boolean = false,
)
