package com.example.sleepapp

import android.app.Application
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sleepapp.dao.Database
import com.example.sleepapp.dao.NotesState
import com.example.sleepapp.dao.NotesViewModel

class App : Application() {
    val database by lazy { Database.createDatabase(this) }
}