package com.example.sleepapp.activities

import ItemNavigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.sleepapp.topappbars.notes.SharedViewModel
import com.example.sleepapp.ui.theme.SleepAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SleepAppTheme {
                val sharedViewModel: SharedViewModel by viewModels()
                ItemNavigation(sharedViewModel)
            }
        }
    }
}
