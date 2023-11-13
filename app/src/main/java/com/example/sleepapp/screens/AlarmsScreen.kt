
import android.content.Context
import android.text.Layout.Alignment
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sleepapp.notesscreen.NoteItem
import com.example.sleepapp.ui.theme.AlarmCardColor
import com.example.sleepapp.ui.theme.CheckedSwitchTrackColor
import com.example.sleepapp.ui.theme.CheckedThumbColor

@Composable
fun AlarmsScreen(context: Context) {

    var alarms = listOf("15:00", "15:00", "15:00", "15:00", "15:00", "15:00", "15:00", "15:00")

    LazyColumn(
        modifier = Modifier
            .padding(5.dp)
    ) {
        itemsIndexed(alarms) { _, item ->
            Alarm(item, context)
        }
    }
}
@Composable
fun Alarm(str: String, context: Context){

    var alarmDescription by remember {
        mutableStateOf("сб, 15 окт.")
    }

    var isSwitchIsChecked by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = AlarmCardColor, // Set the background color
            contentColor = Color.White // Set the content color
        ),
        modifier = Modifier
            .padding(5.dp).sizeIn(minWidth = 20.dp, minHeight = 130.dp).fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = str,
                fontSize = 32.sp,
                modifier = Modifier.padding(top=20.dp),
            )
            Text(
                text = alarmDescription,
                modifier = Modifier
                    .padding(start=80.dp, top=30.dp),

            )
            Switch(
                checked = isSwitchIsChecked,
                onCheckedChange = {isSwitchIsChecked = it},
                modifier = Modifier.padding(top=20.dp),
                colors = SwitchDefaults.colors(
                    checkedTrackColor = CheckedSwitchTrackColor,
                    checkedThumbColor = CheckedThumbColor
                )
            )
            if(isSwitchIsChecked){Toast.makeText(context, "Dwdw", Toast.LENGTH_SHORT).show()}
        }
    }
}