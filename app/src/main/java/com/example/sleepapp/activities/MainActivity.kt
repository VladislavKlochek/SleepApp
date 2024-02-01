package com.example.sleepapp.activities

import ItemNavigation
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.sleepapp.ui.theme.SleepAppTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SleepAppTheme {
                ItemNavigation(this)
            }
        }
    }
}
