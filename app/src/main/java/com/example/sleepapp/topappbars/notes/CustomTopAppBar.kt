package com.example.sleepapp.topappbars.notes

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sleepapp.R

@Composable
fun CustomTopAppBar(
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String,

) {
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultTopAppBar(
                onSearchClicked = {
                    sharedViewModel.searchAppBarState.value =
                        SearchAppBarState.OPENED

                }
            )
        }
        else -> {
            SearchTopAppBar(
                text = searchTextState,
                onTextChange = { text ->
                    sharedViewModel.searchTextState.value = text
                },
                onCloseClicked = {
                    sharedViewModel.searchAppBarState.value =
                        SearchAppBarState.CLOSED
                    sharedViewModel.searchTextState.value = ""
                },
                onSearchClicked = {}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBar(
    onSearchClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
    ) {
        TopAppBar(
            modifier = Modifier.padding(top = 24.dp),
            title = {
                Text(
                    text = "Поиск по записям",
                    color = Color.White,
                    fontSize = 10.sp
                )
            },
            actions = {
                AppBarActions(
                    onSearchClicked = onSearchClicked
                )
            }
        )
    }
}

@Composable
fun AppBarActions(
    onSearchClicked: () -> Unit
) {
    SearchAction(onSearchClicked = onSearchClicked)
    TagsAction()
    CalendarAction()
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {
    val context = LocalContext.current
    IconButton(
        onClick = {
            onSearchClicked()
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "search_icon",
            tint = Color.White
        )
    }
}

@Composable
fun TagsAction() {
    IconButton(
        onClick = {
            
        }
    ) {
        Text(text = "#")
    }
}

@Composable
fun CalendarAction() {
    IconButton(
        onClick = {

        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.calendar_icon),
            contentDescription = "calendar_icon",
            tint = Color.White
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    focusRequester: FocusRequester = remember { FocusRequester() }
) {

    var trailingIconState by remember {
        mutableStateOf(TrailingIconState.DELETE)
    }

    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
    ) {

        Surface(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxSize(),
            color = Color.Transparent,
        ) {
            TextField(
                modifier = Modifier.fillMaxSize().focusRequester(focusRequester).onGloballyPositioned { focusRequester.requestFocus() },
                value = text,
                onValueChange = {
                    onTextChange(it)
                },
                placeholder = {
                    Text(
                        modifier = Modifier
                            .alpha(3.0f),
                        text = stringResource(id = R.string.search),
                        color = Color.White
                    )
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                ),
                singleLine = true,
                leadingIcon = {
                    IconButton(
                        modifier = Modifier
                            .alpha(3.0f),
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = stringResource(
                                id = R.string.search_icon
                            ),
                            tint = Color.White
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = {
                        when (trailingIconState) {
                            TrailingIconState.DELETE -> {
                                if(text.isEmpty()){
                                    onCloseClicked()
                                }
                                else {
                                    onTextChange("")
                                    trailingIconState = TrailingIconState.CLOSE
                                }
                            }
                            TrailingIconState.CLOSE -> {
                                if (text.isNotEmpty()) {
                                    onTextChange("")
                                } else {
                                    onCloseClicked()
                                    trailingIconState = TrailingIconState.DELETE
                                }
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = stringResource(id = R.string.close_icon),
                            tint = Color.White
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClicked(text)
                    }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )
        }

    }
}
