package com.example.sleepapp.notesscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sleepapp.R
import com.example.sleepapp.ui.theme.noteCardColor
import com.example.sleepapp.ui.theme.noteCardTagsBackgroundColor


@Composable
fun NoteItem() {
    val items = listOf(
        "tag1",
        "tag2",
        "tag3",
        "tag4",
        "tag5",
        "tag2",
        "tag3",
        "tag4",
        "tag5",
        "tag2",
        "tag3",
        "tag4",
        "tag5",
        "tag2",
        "tag3",
        "tag4",
        "tag5",
        "tag2",
        "tag3",
        "tag4",
        "tag5"
    )
    Card(
        modifier = Modifier
            .sizeIn(200.dp, 200.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(20.dp),
        content = {
            Column(modifier = Modifier.background(noteCardColor),
                content = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 10.dp, 0.dp, 0.dp),
                        content = {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .padding(25.dp, 0.dp, 0.dp, 0.dp),
                                content = {
                                    Text(
                                        text = "27.07.2023",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Thin,
                                        color = Color.Black
                                    )
                                },
                                contentAlignment = Alignment.TopStart
                            );
                            Box(
                                modifier = Modifier.fillMaxWidth(0.6f),
                                content = {
                                    Text(
                                        text = "Название",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                },
                                contentAlignment = Alignment.BottomEnd
                            );

                        },
                        /*horizontalArrangement = Arrangement.SpaceAround*/
                    );
                    Text(
                        text = "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",
                        modifier = Modifier.padding(10.dp),
                        maxLines = 5,
                        color = Color.Black
                    )
                    Row(
                        content = {
                            Card(
                                shape = RoundedCornerShape(15.dp),
                                modifier = Modifier
                                    .background(noteCardColor)
                                    .padding(20.dp, 0.dp, 0.dp, 10.dp)
                                    .fillMaxWidth(0.7f)
                            ) {

                                LazyRow(
                                    content = {
                                        itemsIndexed(items) { index, item ->
                                            Text(
                                                text = item,
                                                color = Color.Blue,
                                                modifier = Modifier.padding(5.dp)
                                            )
                                        }
                                    },
                                    modifier = Modifier.background(noteCardTagsBackgroundColor)
                                )
                            }
                            Row(modifier = Modifier.padding(15.dp,0.dp,15.dp,0.dp).fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                Image(
                                    painter = painterResource(id = R.drawable.coffee_icon),
                                    contentDescription = null,

                                )
                                Image(
                                    painter = painterResource(id = R.drawable.alcohol_icon),
                                    contentDescription = null,

                                )
                            }
                            /*Box(modifier = Modifier.fillMaxWidth(0.5f), contentAlignment = Alignment.BottomEnd){
                                Image(
                                    painter = painterResource(id = R.drawable.coffee_icon),
                                    contentDescription = null,
                                )
                            }
                            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd){
                                Image(
                                    painter = painterResource(id = R.drawable.alcohol_icon),
                                    contentDescription = null,
                                )
                            }*/
                        }
                    )
                }
            )
        }
    )
}
