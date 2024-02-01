package com.example.sleepapp

import android.app.Application
import com.example.sleepapp.dao.Database

class App : Application() {
    val database by lazy { Database.createDatabase(this) }
}