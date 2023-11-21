import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sleepapp.dao.NotesViewModel


@Composable
fun NavGraph(navHostController: NavHostController, activityContext: Context, notesViewModel: NotesViewModel) {
    NavHost(navController = navHostController, startDestination = "notes_screen"){
        composable("notes_screen"){ NotesScreen(activityContext, notesViewModel)}
        composable("alarms_screen"){ AlarmsScreen()}
        composable("information_screen"){ InformationScreen()}
    }
}