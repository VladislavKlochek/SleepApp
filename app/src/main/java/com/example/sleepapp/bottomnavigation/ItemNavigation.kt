
import android.app.Activity
import android.content.Context
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
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.example.sleepapp.bottomnavigation.BottomItem
import com.example.sleepapp.topappbars.notes.CustomTopAppBar
import com.example.sleepapp.topappbars.notes.SearchAppBarState
import com.example.sleepapp.topappbars.notes.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemNavigation(sharedViewModel1: SharedViewModel, activityContext: Context) {
    val listItems = listOf(BottomItem.Notes, BottomItem.Alarms, BottomItem.Information)
    val navController = rememberNavController()

    var selectedItemIndex by remember {
        mutableIntStateOf(0)
    }

    val searchAppBarState: SearchAppBarState by sharedViewModel1.searchAppBarState
    val searchTextState: String by sharedViewModel1.searchTextState


    Scaffold(
        bottomBar = {
            NavigationBar {
                listItems.forEachIndexed { index, bottomItem ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index;
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
            if (selectedItemIndex != 2) FloatingActionButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        topBar = {
            if (selectedItemIndex == 0) CustomTopAppBar(sharedViewModel1, searchAppBarState, searchTextState)
        }
    ) {

        // Apply padding to the Column to control content placement
        Column(
            modifier = Modifier
                .padding(
                    bottom = it.calculateBottomPadding(),
                    top = it.calculateTopPadding()
                ), // Adjust the padding here
        ) {
            // Your content here
            NavGraph(navHostController = navController, activityContext)
        }


    }
}
