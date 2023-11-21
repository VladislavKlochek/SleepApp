package com.example.sleepapp.notesscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun NoteItem(){
    Card(
        modifier = Modifier
            .sizeIn(200.dp, 200.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        content = {
            Box(){
            Box(contentAlignment = Alignment.TopStart, content = { Text(text = "testdsdsdsd") })
            Box(contentAlignment = Alignment.TopEnd, content = { Text(text = "leftone") })
            Box(contentAlignment = Alignment.Center, content = {
                Text(
                    text = "leftodbwoudbwudbiwuabduwabdiubsaiubwuadbabdwiubduiwabduiawbdbawiudbawdawudne",
                    textAlign = TextAlign.Center,
                )
            })
            Box(contentAlignment = Alignment.BottomStart,
                content = {
                    LazyRow(content = {
                        listOf<String>(
                            "dasdas",
                            "dsadawdwa",
                            "qqqqqqqqqqqq",
                            "ccccccccccccccccccccccccc"
                        )
                    })
                })
        }
        }
    )
}