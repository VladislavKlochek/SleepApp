package com.example.sleepapp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sleepapp.R
import com.example.sleepapp.notesscreen.note
import com.example.sleepapp.ui.theme.noteBackColor
import com.example.sleepapp.ui.theme.noteCardColor
import com.example.sleepapp.ui.theme.noteDeleteColor
import java.text.SimpleDateFormat
import java.util.Locale


class NoteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateActivity(note = intent.extras?.getSerializable("note") as note)
        }
    }
}

@Composable
fun CreateActivity(note: note) {
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
    var text by remember { mutableStateOf(note.text) }
    var isPopupVisible by remember { mutableStateOf(false) }

    var isAddOpen by remember { mutableStateOf(false) }
    var tagText by remember { mutableStateOf("") }

    var noteName by remember { mutableStateOf(note.noteName) }
    Card(
        shape = RectangleShape
    ) {
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
                        Row(
                            modifier = Modifier
                                .padding(0.dp, 0.dp, 15.dp, 0.dp)
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

                    },
                );
                Row(
                    content = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            content = {
                                BasicTextField(
                                    value = noteName,
                                    onValueChange = { newText ->
                                        noteName = newText
                                    },
                                    textStyle = TextStyle(
                                        fontWeight = FontWeight.Medium,
                                        textAlign = TextAlign.Center,
                                        fontSize = 20.sp,
                                        color = Color.Black ),
                                    modifier = Modifier.padding(top = 10.dp)
                                )
                            },
                            contentAlignment = Alignment.BottomCenter
                        )
                    }
                )
                OutlinedTextField(
                    value = text,
                    onValueChange = {
                        // Обновите текст, когда пользователь вводит новое значение
                        text = it
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.None
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            // Вызывается, когда пользователь завершает ввод (например, нажимает Enter или "Готово" на клавиатуре)
                        }
                    ),
                    modifier = Modifier
                        .padding(10.dp, 10.dp, 10.dp, 10.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f),
                    textStyle = TextStyle(fontSize = 22.sp, textIndent = TextIndent(40.sp, 15.sp))
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    content = {
                        Button(
                            onClick = { /*TODO*/ },
                            content = {
                                Text(text = "Удалить")
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 1.dp)
                                .fillMaxSize(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = noteDeleteColor, // Здесь вы можете указать желаемый цвет фона
                                contentColor = Color.White // Здесь вы можете указать цвет текста кнопки
                            ),
                            shape = RoundedCornerShape(15.dp, 0.dp, 0.dp, 0.dp)
                        )
                        Button(
                            onClick = {
                                isPopupVisible = !isPopupVisible
                            },
                            content = {
                                Text(text = "Теги")
                            },
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize(),
                            shape = RectangleShape
                        )
                        Button(
                            onClick = { /*TODO*/ },
                            content = {
                                Text(text = "Назад") //Сохранить изменения
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = noteBackColor, // Здесь вы можете указать желаемый цвет фона
                                contentColor = Color.White // Здесь вы можете указать цвет текста кнопки
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 1.dp)
                                .fillMaxSize(),
                            shape = RoundedCornerShape(0.dp, 15.dp, 0.dp, 0.dp)
                        )

                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        )
    }
    if (isPopupVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable { isPopupVisible = false }
        ) {
            Card(
                content = {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LazyColumn(
                            content = {items(note.tags) { TagItem(tag = it.substring(1))} },
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.8f)
                        )
                        Box(modifier = Modifier.fillMaxSize()) {
                            SmallFloatingActionButton(
                                onClick = {
                                    isAddOpen = !isAddOpen
                                },
                                modifier = Modifier
                                    .size(70.dp)
                                    .align(Alignment.Center),
                                shape = androidx.compose.foundation.shape.CircleShape,
                                content = {
                                    Text(
                                        text = "+", fontSize = 40.sp, modifier = Modifier.align(
                                            Alignment.Center
                                        )
                                    )
                                }
                            )
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .fillMaxHeight(0.5f)
                    .align(Alignment.Center)
                    .wrapContentSize(Alignment.Center),
            )
        }
    }
    if (isAddOpen) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.7f))
                .clickable { isAddOpen = false }
        ) {
            Card(
                content = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        TextField(
                            value = tagText,
                            onValueChange = { tagText = it },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            textStyle = TextStyle(fontSize = 20.sp)
                        )
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            border = BorderStroke(0.dp, Color.Transparent),
                            modifier = Modifier.fillMaxSize(),
                            shape = RectangleShape,
                            content = {
                                Text(
                                    text = "Сохранить",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .wrapContentSize(Alignment.Center)
                                )
                            }
                        )
                    }
                },
                /*modifier = Modifier.background(Color.Red).fillMaxHeight(0.8f).fillMaxWidth(), fontSize = 20.sp*/
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .fillMaxHeight(0.15f)
                    .align(Alignment.Center)
                    .wrapContentSize(Alignment.Center)
            )
        }
    }

}

@Composable
fun TagItem(tag: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .borderOnlyBottom(BorderStroke(1.dp, Color.Black)),
        content = {
            Text(
                text = "#", modifier = Modifier
                    .fillMaxWidth(0.1f)
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
                    , fontSize = 20.sp, textAlign = TextAlign.End
            )
            Text(
                text = tag, modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)

                    .padding(start = 5.dp), fontSize = 20.sp
            )
            IconButton(
                onClick = { /*TODO*/ },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.delete_tag_icon),
                        contentDescription = "delete tag"
                    )
                },
                modifier = Modifier
                    .fillMaxSize()

            )
        },
    )
}
fun Modifier.borderOnlyBottom(
    borderStroke: BorderStroke
) = this.then(
    Modifier.drawBehind {
        val strokeWidth = borderStroke.width.toPx()
        val y = size.height - strokeWidth
        drawLine(
            brush = borderStroke.brush,
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = strokeWidth
        )
    }
)