package com.example.sleepapp.bottomnavigation

import com.example.sleepapp.R

sealed class BottomItem(val title: String, val iconId: Int, val route: String){
    object Notes: BottomItem("Notes", R.drawable.notes, "notes_screen")
    object Alarms: BottomItem("Alarm", R.drawable.alarm, "alarms_screen")
    object Information: BottomItem("Information", R.drawable.info, "information_screen")
}
