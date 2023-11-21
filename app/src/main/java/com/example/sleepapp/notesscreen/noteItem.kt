package com.example.sleepapp.notesscreen

import android.icu.text.DateFormat
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.widget.TextViewCompat.AutoSizeTextType
import com.example.sleepapp.R
import com.example.sleepapp.ui.theme.noteCardColor
import com.example.sleepapp.ui.theme.noteCardTagsBackgroundColor
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun NoteItem(note: note) {
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
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
                                        text = formatter
                                            .format(note.date),
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = Color.Black
                                    )
                                },
                                contentAlignment = Alignment.TopStart
                            );
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                content = {

                                    Text(
                                        text = note.noteName,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = Color.Black,
                                        textAlign = TextAlign.Center,

                                    )


                                },
                                contentAlignment = Alignment.BottomCenter
                            );

                        },
                    );
                    Text(
                        text = note.text,
                        modifier = Modifier.padding(0.dp, 10.dp, 10.dp, 10.dp),
                        maxLines = 5,
                        color = Color.Black,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(textIndent = TextIndent(50.sp, 25.sp))
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
                                        itemsIndexed(note.tags) { index, item ->
                                            Text(
                                                AnnotatedString(text = item + if (index < note.tags.size - 1) ", " else ""),
                                                color = Color.Blue,
                                                modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 5.dp)
                                            )
                                        }
                                    },
                                    modifier = Modifier.background(noteCardTagsBackgroundColor)
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .padding(15.dp, 0.dp, 15.dp, 0.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                if (note.coffee) {
                                    Image(
                                        painter = painterResource(id = R.drawable.coffee_icon),
                                        contentDescription = null,
                                        )
                                }
                                if (note.alcohol) {
                                    Image(
                                        painter = painterResource(id = R.drawable.alcohol_icon),
                                        contentDescription = null,
                                        )
                                }
                            }
                        }
                    )
                }
            )
        }
    )
}
