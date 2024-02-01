import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.core.app.ComponentActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.sleepapp.AlarmsViewModelSingleton
import com.example.sleepapp.AlarmsViewModelSingleton.alarmsViewModel
import com.example.sleepapp.activities.AddNoteActivity
import com.example.sleepapp.alarmsscreen.Alarm
import com.example.sleepapp.bottomnavigation.BottomItem
import com.example.sleepapp.dao.AlarmsViewModel
import com.example.sleepapp.dao.NotesViewModel
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection


@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemNavigation(activity: ComponentActivity) {
    val listItems = listOf(BottomItem.Notes, BottomItem.Alarms, BottomItem.Information)
    val navController = rememberNavController()

    var selectedItemIndex by remember {
        mutableIntStateOf(0)
    }

    val notesViewModel: NotesViewModel = viewModel(factory = NotesViewModel.factory)

    val alarmsViewModel22: AlarmsViewModel = viewModel(factory = AlarmsViewModel.factory)
    AlarmsViewModelSingleton.initialize(alarmsViewModel22)

    val clockState = rememberSheetState()


    var isAccessToNotificationManagerGranted = false
    lateinit var pLauncher: ActivityResultLauncher<String>
    pLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            isAccessToNotificationManagerGranted = true;
        } else {
            Toast.makeText(activity, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    ClockDialog(
        state = clockState,
        config = ClockConfig(
            is24HourFormat = true
        ),
        selection = ClockSelection.HoursMinutes { hours, minutes ->

            val alarm = Alarm(hours, minutes, false)

            alarmsViewModel.insertItem(alarm)

        })

    Scaffold(
        bottomBar = {
            NavigationBar {
                listItems.forEachIndexed { index, bottomItem ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(bottomItem.route)
                        },
                        icon = {
                            Icon(
                                painterResource(id = bottomItem.iconId),
                                contentDescription = bottomItem.title
                            )
                        },
                        label = {
                            Text(text = bottomItem.title)
                        }
                    )
                }
            }
        },
        floatingActionButton = {
            if (selectedItemIndex == 0) FloatingActionButton(
                onClick = {

                    val intent = Intent(activity.applicationContext, AddNoteActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(activity.applicationContext, intent, null)

                },
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
            }
            else if (selectedItemIndex == 1) FloatingActionButton(
                onClick = {

                    if(!Settings.canDrawOverlays(activity.applicationContext) || !isAccessToNotificationManagerGranted) {
                        if (!Settings.canDrawOverlays(activity.applicationContext)) {
                            openOverlayPermissionSettings(activity.applicationContext)
                        }
                        if (!(activity.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).isNotificationPolicyAccessGranted
                        ) {
                            requestNotificationPermission(pLauncher)
                        }
                    }
                    else{clockState.show()}

                },
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) {
        Column(
            modifier = Modifier
                .padding(
                    bottom = it.calculateBottomPadding(),
                    top = it.calculateTopPadding()
                ),
        ) {
            NavGraph(
                navHostController = navController,
                activity.applicationContext,
                notesViewModel,
                alarmsViewModel
            )
        }
    }


}
fun requestNotificationPermission(pLauncher: ActivityResultLauncher<String>) {
    pLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
}
fun openOverlayPermissionSettings(context: Context) {
    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
    intent.data = Uri.parse("package:" + context.packageName)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(context, intent, null)
}


