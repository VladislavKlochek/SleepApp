import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "notes_screen"){
        composable("notes_screen"){ NotesScreen()}
        composable("alarms_screen"){ AlarmsScreen()}
        composable("information_screen"){ InformationScreen()}
    }
}