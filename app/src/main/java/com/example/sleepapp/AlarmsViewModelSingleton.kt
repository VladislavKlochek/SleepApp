package com.example.sleepapp

import com.example.sleepapp.dao.AlarmsViewModel

object AlarmsViewModelSingleton {
    lateinit var alarmsViewModel: AlarmsViewModel
        private set

    fun initialize(alarmsViewModel: AlarmsViewModel) {
        this.alarmsViewModel = alarmsViewModel
    }
}
