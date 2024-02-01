import android.app.AlarmManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sleepapp.MyIntents
import com.example.sleepapp.alarmsscreen.AlarmItem
import com.example.sleepapp.dao.AlarmsViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun AlarmsScreen(
    activityContext: Context,
    alarmsViewModel: AlarmsViewModel
) {
    val alarmsList = alarmsViewModel.itemsList.collectAsState(initial = emptyList())
    LazyColumn(
        modifier = Modifier
            .padding(5.dp)
    ) {
        itemsIndexed(alarmsList.value) { index, item ->
            val archive = SwipeAction(
                onSwipe = {
                    val alarmManager =
                        activityContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    val myIntents = MyIntents(activityContext)
                    alarmManager.cancel(myIntents.getAlarmActionPendingIntent())
                    alarmsViewModel.deleteAlarm(item)
                },
                icon = {
                    Icon(
                        modifier = Modifier.padding(16.dp),
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.White
                    )
                },
                background = Color.Red
            )
            SwipeableActionsBox(endActions = listOf(archive)) {
                AlarmItem(
                    alarm = item,
                    context = activityContext,
                    alarmsViewModel = alarmsViewModel
                )
            }
        }
    }
}

