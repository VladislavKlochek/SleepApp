package com.example.sleepapp

import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sleepapp.dao.AlarmsViewModel
import com.example.sleepapp.ui.theme.noteCardColor
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class AlarmActivity : ComponentActivity() {

    private var ringtone: Ringtone? = null
    private lateinit var alarmsViewModel: AlarmsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alarmsViewModel = AlarmsViewModelSingleton.alarmsViewModel

        setContent {
            CreateView()
        }

        val notificationUri: Uri? = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone = RingtoneManager.getRingtone(this, notificationUri)
        if (ringtone == null) {
            val newNotificationUri: Uri? =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            ringtone = RingtoneManager.getRingtone(this, newNotificationUri)
        }
        ringtone?.play()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        window.addFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                    or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    override fun onDestroy() {
        ringtone?.takeIf { it.isPlaying }?.stop()
        super.onDestroy()
    }
}

@Composable
fun CreateView() {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(

                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Текущее время:",
                        fontSize = 20.sp,
                        color = noteCardColor,
                        letterSpacing = 5.sp,
                    )
                    Text(
                        text = LocalTime.now()
                            .format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)),
                        fontSize = 64.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        fontStyle = FontStyle(4),
                        fontFamily = FontFamily.Serif
                    )

                }
            }

            ElevatedButton(
                onClick = { onBackPressedDispatcher?.onBackPressed() },
                modifier = Modifier.size(300.dp, 200.dp)
            ) {
                Text(text = "Отключить", fontSize = 32.sp)
            }
        }

    }
}
