package com.example.sleepapp.topappbars.notes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SharedViewModel(): ViewModel() {
    val searchAppBarState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)
    val searchTextState: MutableState<String> = mutableStateOf("")
}