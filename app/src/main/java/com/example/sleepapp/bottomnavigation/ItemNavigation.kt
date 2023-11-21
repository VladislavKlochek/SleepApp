import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemNavigation() {
    val listItems = listOf(BottomItem.Notes, BottomItem.Alarms, BottomItem.Information)
    val navController = rememberNavController()

    var selectedItemIndex by remember {
        mutableIntStateOf(0)
    }

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
        floatingActionButtonPosition = FabPosition.End
    ) {

        // Apply padding to the Column to control content placement
        Column(
            modifier = Modifier
                .padding(bottom = it.calculateBottomPadding()), // Adjust the padding here
        ) {
            // Your content here
            NavGraph(navHostController = navController)
        }


    }
}


