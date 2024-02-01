package com.example.sleepapp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.sleepapp.activities.ui.theme.SleepAppTheme
import com.example.sleepapp.ui.theme.InfoCardColor

class InformationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SleepAppTheme {
                intent.getStringExtra("text")?.let { CreateTextToRead(it) }
            }
        }
    }
}

@Composable
fun CreateTextToRead(text: String) {
    var text1 = text
    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = InfoCardColor
        )

    ) {
        OutlinedTextField(
            value = text1,
            onValueChange = {
                val newText = it.replace("\n", "\n\n")
                text1 = newText
            },
            modifier = Modifier.fillMaxSize(),
            readOnly = true,
            textStyle = TextStyle(
                fontSize = 20.sp,
            ),
            label = { "test" }
        )
    }
}
