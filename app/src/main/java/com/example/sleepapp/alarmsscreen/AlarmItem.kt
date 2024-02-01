package com.example.sleepapp.alarmsscreen

import android.app.AlarmManager
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.sleepapp.MyIntents
import com.example.sleepapp.dao.AlarmsViewModel
import com.example.sleepapp.ui.theme.AlarmCardColor
import com.example.sleepapp.ui.theme.CheckedSwitchTrackColor
import com.example.sleepapp.ui.theme.CheckedThumbColor
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmItem(
    alarm: Alarm,
    context: Context,
    alarmsViewModel: AlarmsViewModel
) {

    var isSwitchChecked by remember { mutableStateOf(alarm.isSet) }
    val clockState = rememberSheetState()
    ClockDialog(
        state = clockState,
        config = ClockConfig(
            is24HourFormat = true
        ),
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            alarm.hours = hours
            alarm.minutes = minutes
            alarm.isSet = false
            alarmsViewModel.insertItem(alarm)
        })

    Card(
        colors = CardDefaults.cardColors(
            containerColor = AlarmCardColor,
            contentColor = Color.White
        ),
        modifier = Modifier
            .padding(5.dp)
            .sizeIn(minWidth = 20.dp, minHeight = 130.dp)
            .fillMaxWidth()
            .clickable {
                clockState.show()
            },
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle()
                    ) {
                        append(
                            "${
                                alarm.hours.toString().padStart(2, '0')
                            }:${alarm.minutes.toString().padStart(2, '0')}"
                        )
                    }
                },
                fontSize = 32.sp,
                modifier = Modifier.padding(top = 20.dp),
            )
            Text(
                text = getBeautyDate(alarm.hours, alarm.minutes),
                modifier = Modifier
                    .padding(start = 80.dp, top = 30.dp),

                )
            Switch(
                checked = isSwitchChecked,
                onCheckedChange = { isChecked ->
                    isSwitchChecked = isChecked
                    alarm.isSet = isChecked
                    alarmsViewModel.updateSwitchState(alarm)
                    if (isChecked) {
                        setAlarm(alarm, context)
                    } else {
                        cancelAlarm(context, alarm)
                    }
                },
                modifier = Modifier.padding(top = 20.dp),
                colors = SwitchDefaults.colors(
                    checkedTrackColor = CheckedSwitchTrackColor,
                    checkedThumbColor = CheckedThumbColor
                )
            )
        }
    }
}

fun getBeautyDate(hours: Int, minutes: Int): String {
    val currentTime = LocalTime.now()
    val specifiedTime = LocalTime.of(hours, minutes)

    val resDate: LocalDate =
        if (currentTime.isBefore(specifiedTime) || currentTime == specifiedTime && LocalTime.now().minute < minutes) {
            LocalDate.now()
        } else {
            LocalDate.now().plusDays(1)
        }

    val formatter = DateTimeFormatter.ofPattern(
        "EEE, dd MMM",
        Locale("ru")
    )
    return resDate.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.S)
fun setAlarm(alarm: Alarm, context: Context) {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    val alarmManager =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    val calendar = Calendar.getInstance()
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    calendar.set(Calendar.MINUTE, alarm.minutes)
    calendar.set(Calendar.HOUR_OF_DAY, alarm.hours)

    val myIntents = MyIntents(context)

    val alarmClockInfo: AlarmManager.AlarmClockInfo
    val currentTime = Calendar.getInstance()
    alarmClockInfo = if (calendar.timeInMillis < currentTime.timeInMillis) {
        calendar.add(Calendar.HOUR_OF_DAY, 24)
        AlarmManager.AlarmClockInfo(
            calendar.timeInMillis,
            myIntents.getAlarmInfoPendingIntent()
        )
    } else {
        AlarmManager.AlarmClockInfo(
            calendar.timeInMillis,
            myIntents.getAlarmInfoPendingIntent()
        )
    }

    val canScheduleExact = alarmManager.canScheduleExactAlarms()
    if (!canScheduleExact) {
        alarmManager.setAlarmClock(
            alarmClockInfo,
            myIntents.getAlarmActionPendingIntent()
        )
    } else {
        alarmManager.setAlarmClock(
            alarmClockInfo,
            myIntents.getAlarmActionPendingIntent()
        )
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            myIntents.getAlarmActionPendingIntent()
        )
    }


    Toast.makeText(
        context,
        "Будильник установлен на " + sdf.format(calendar.time),
        Toast.LENGTH_SHORT
    ).show()
}

fun cancelAlarm(context: Context, alarm: Alarm) {
    val alarmManager =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val myIntents = MyIntents(context)
    alarmManager.cancel(myIntents.getAlarmActionPendingIntent())
    Toast.makeText(context, "Будильник отключен", Toast.LENGTH_SHORT)
        .show()
}