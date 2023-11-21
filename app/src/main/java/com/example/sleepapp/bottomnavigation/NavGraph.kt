import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun NavGraph(navHostController: NavHostController, activityContext: Context) {
    NavHost(navController = navHostController, startDestination = "notes_screen"){
        composable("notes_screen"){ NotesScreen(activityContext)}
        composable("alarms_screen"){ AlarmsScreen()}
        composable("information_screen"){ InformationScreen()}
    }
}