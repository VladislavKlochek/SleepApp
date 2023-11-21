package com.example.sleepapp.activities

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sleepapp.R
import com.example.sleepapp.ui.theme.DarkGreen
import com.example.sleepapp.ui.theme.LightPink
import com.example.sleepapp.ui.theme.LightPurple
import com.example.sleepapp.ui.theme.NoteRedColor
import com.example.sleepapp.ui.theme.PinkDark
import com.example.sleepapp.ui.theme.PurpleDark
import com.example.sleepapp.ui.theme.noteCardColor
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeker.sheets.core.utils.BaseConstants
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale

class AddNoteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateAddActivity(baseContext)

        }
    }
}

val Tags = mutableStateListOf<String>()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAddActivity(
    context: Context,
    focusRequester: FocusRequester = remember { FocusRequester() }
) {
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)
    var isDateAdded by remember {
        mutableStateOf(false)
    }
    var addedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    var isNameAdded by remember {
        mutableStateOf(false)
    }
    var addedName by remember {
        mutableStateOf("")
    }
    var isAffectedByCoffeeAdded by remember {
        mutableStateOf(false)
    }
    var isAffectedByAlcoholAdded by remember {
        mutableStateOf(false)
    }

    var NoteText by remember {
        mutableStateOf("")
    }

    var isAddNameShowing by remember {
        mutableStateOf(false)
    }

    var isAddTagsVisible by remember {
        mutableStateOf(false)
    }
    var isAddTagShowing by remember {
        mutableStateOf(false)
    }
    var tagToSave by remember {
        mutableStateOf("")
    }

    var isAddFactorCardShowing by remember {
        mutableStateOf(false)
    }

    var alcoholButtonColor by remember { mutableStateOf(PurpleDark) }
    var coffeeButtonColor by remember { mutableStateOf(PurpleDark) }

    val calendarState = rememberSheetState()

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true
        ),
        selection = CalendarSelection.Date{
                date ->
            run { addedDate = date; isDateAdded = true }
        }
    )
    Card(
        shape = RectangleShape
    ) {
        Column(modifier = Modifier.background(noteCardColor), content = {
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
                            if (isDateAdded) {
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(style = SpanStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.Black)) {
                                            append("${addedDate.dayOfMonth}.${addedDate.monthValue}.${addedDate.year}")
                                        }},
                                    modifier = Modifier.clickable { calendarState.show() }
                                )
                            }
                        },
                        contentAlignment = Alignment.TopStart
                    );
                    Row(
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 15.dp, 0.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        if (isAffectedByCoffeeAdded) {
                            Image(
                                painter = painterResource(id = R.drawable.coffee_icon),
                                contentDescription = null,
                            )
                        }
                        if (isAffectedByAlcoholAdded) {
                            Image(
                                painter = painterResource(id = R.drawable.alcohol_icon),
                                contentDescription = null,
                            )
                        }
                    }

                },
            );
            Row(content = {
                Box(
                    modifier = Modifier.fillMaxWidth(), content = {
                        if (isNameAdded) Text(
                            text = addedName,
                            modifier = Modifier.padding(top = 10.dp).clickable { isAddNameShowing = true},
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    }, contentAlignment = Alignment.BottomCenter
                )
            })
            OutlinedTextField(
                value = NoteText,
                onValueChange = {
                    // Обновите текст, когда пользователь вводит новое значение
                    NoteText = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text, imeAction = ImeAction.None
                ),
                keyboardActions = KeyboardActions(onDone = {
                    // Вызывается, когда пользователь завершает ввод (например, нажимает Enter или "Готово" на клавиатуре)
                }),
                modifier = Modifier
                    .padding(10.dp, 10.dp, 10.dp, 10.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.87f),
                textStyle = TextStyle(fontSize = 22.sp, textIndent = TextIndent(40.sp, 15.sp))
            )
            Row(
                modifier = Modifier.fillMaxSize(),
                content = {
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = {
                                calendarState.show()
                            },
                            modifier = Modifier
                                .weight(1f)
                                .size(100.dp, 0.dp), colors = ButtonDefaults.buttonColors(
                                containerColor = PurpleDark,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(0.dp, 30.dp, 30.dp, 0.dp)
                        ) {
                            Text(text = "Дата")
                        }
                        Button(
                            onClick = { isAddNameShowing = true }, modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(end = 3.dp), colors = ButtonDefaults.buttonColors(
                                containerColor = LightPurple,
                                contentColor = Color.White
                            ),
                            shape = AbsoluteRoundedCornerShape(0.dp, 30.dp, 0.dp, 0.dp)
                        ) {
                            Text(text = "Название")
                        }
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = { /*TODO*/ }, modifier = Modifier.fillMaxSize(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = DarkGreen,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp)
                        ) {
                            Text(text = "Готово")
                        }
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Button(
                            onClick = { isAddTagsVisible = true }, modifier = Modifier
                                .weight(1f)
                                .size(100.dp, 0.dp)
                                .align(Alignment.End), colors = ButtonDefaults.buttonColors(
                                containerColor = PinkDark,
                                contentColor = Color.White
                            ), shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 30.dp)
                        ) {
                            Text(text = "Теги")
                        }
                        Button(
                            onClick = { isAddFactorCardShowing = !isAddFactorCardShowing },
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.End)
                                .fillMaxWidth()
                                .padding(start = 3.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = LightPink,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(30.dp, 0.dp, 0.dp, 0.dp)
                        ) {
                            Text(text = "Факторы")
                        }
                    }
                })


        })
    }
    if (isAddTagsVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable { isAddTagsVisible = false }
        ) {
            Card(
                content = {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LazyColumn(
                            content = { items(Tags) { TagItem1(tag = it) } },
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.8f)
                        )
                        Box(modifier = Modifier.fillMaxSize()) {
                            SmallFloatingActionButton(
                                onClick = {
                                    isAddTagShowing = !isAddTagShowing
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
    if (isAddNameShowing) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.7f))
                .clickable { isAddNameShowing = false }
        ) {
            Card(
                content = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        TextField(
                            value = addedName,
                            onValueChange = { addedName = it },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            textStyle = TextStyle(fontSize = 20.sp)
                        )
                        OutlinedButton(
                            onClick = { isNameAdded = true; isAddNameShowing = false },
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
    if (isAddTagShowing) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.7f))
                .clickable { isAddTagShowing = false }
        ) {
            Card(
                content = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        TextField(
                            value = tagToSave,
                            onValueChange = { tagToSave = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester)
                                .onGloballyPositioned { focusRequester.requestFocus() },
                            singleLine = true,
                            textStyle = TextStyle(fontSize = 20.sp)
                        )
                        OutlinedButton(
                            onClick = {
                                isAddTagShowing = false;
                                if (!tagToSave.isEmpty() || !tagToSave.isBlank()) {
                                    Tags.add(tagToSave);
                                    tagToSave = ""
                                } else {
                                    Toast.makeText(context, "Введите тег", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            },
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
    if (isAddFactorCardShowing) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.7f))
                .clickable { isAddFactorCardShowing = false }
        ) {
            Card(
                content = {
                    Column () {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1.3f),
                            ) {
                            Button(
                                onClick = {
                                    if (alcoholButtonColor == PurpleDark) {
                                        alcoholButtonColor = NoteRedColor
                                        isAffectedByAlcoholAdded = true
                                    } else {
                                        alcoholButtonColor = PurpleDark;
                                        isAffectedByAlcoholAdded = false
                                    }
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxSize()
                                    .padding(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = alcoholButtonColor,
                                    contentColor = Color.White
                                ),
                                shape = CircleShape
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.alcohol),
                                    contentDescription = "ds"
                                )
                            }
                            Button(
                                onClick = {
                                    if (coffeeButtonColor == PurpleDark) {
                                        coffeeButtonColor = NoteRedColor
                                        isAffectedByCoffeeAdded = true
                                    } else {
                                        coffeeButtonColor = PurpleDark
                                        isAffectedByCoffeeAdded = false
                                    }
                                },
                                modifier = Modifier
                                    .size(200.dp)
                                    .weight(1f)
                                    .fillMaxSize()
                                    .padding(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = coffeeButtonColor,
                                    contentColor = Color.White
                                ),
                                shape = CircleShape
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.coffee_without_background),
                                    contentDescription = "ds",
                                    modifier = Modifier.clip(CircleShape)
                                )
                            }
                        }
                        OutlinedButton(
                            onClick = {
                                isAddFactorCardShowing = false;
                            },
                            border = BorderStroke(0.dp, Color.Transparent),
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f),
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
                    .fillMaxHeight(0.24f)
                    .align(Alignment.Center)
                    .wrapContentSize(Alignment.Center)
            )
        }
    }
}

@Composable
fun TagItem1(tag: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .borderOnlyBottom1(BorderStroke(1.dp, Color.Black)),
        content = {
            Text(
                text = "#", modifier = Modifier
                    .fillMaxWidth(0.1f)
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically), fontSize = 20.sp, textAlign = TextAlign.End
            )
            Text(
                text = tag, modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)

                    .padding(start = 5.dp), fontSize = 20.sp
            )
            IconButton(
                onClick = { Tags.remove(tag) },
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

fun Modifier.borderOnlyBottom1(
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