package com.example.sleepapp.notesscreen

import java.util.Date

data class note(val date: Date, val noteName: String, val text: String, val tags: Array<String>,
                val coffee: Boolean, val alcohol: Boolean)
