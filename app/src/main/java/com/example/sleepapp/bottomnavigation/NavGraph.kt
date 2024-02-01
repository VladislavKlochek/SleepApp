import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sleepapp.dao.AlarmsViewModel
import com.example.sleepapp.dao.NotesViewModel


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun NavGraph(navHostController: NavHostController, activityContext: Context, notesViewModel: NotesViewModel, alarmsViewModel: AlarmsViewModel) {
    NavHost(navController = navHostController, startDestination = "notes_screen"){
        composable("notes_screen"){ NotesScreen(activityContext, notesViewModel)}
        composable("alarms_screen"){ AlarmsScreen(activityContext, alarmsViewModel)}
        composable("information_screen"){ InformationScreen(activityContext)}
    }
}