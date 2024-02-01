package com.example.sleepapp.informationscreen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.sleepapp.activities.InformationActivity
import com.example.sleepapp.ui.theme.InformationCardColor

@Composable
fun TopicItem(item: Topic, context: Context) {
    val lineHeight = 20.sp

    val textStyle = TextStyle(
        fontSize = 16.sp,
        color = Color.White,
        lineHeight = lineHeight
    )
    Card(
        shape = CutCornerShape(15.dp),
        modifier = Modifier.size(180.dp).padding(5.dp).clickable {
            val intent = Intent(context, InformationActivity::class.java)
            intent.putExtra("text", item.text)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            ContextCompat.startActivity(context, intent, null)
        }
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
                .background(InformationCardColor)
                .padding(5.dp).fillMaxSize()

            ) {
            Image(
                painter = painterResource(id = item.imageId),
                contentDescription = null,
                Modifier.size(130.dp),
                contentScale = ContentScale.Fit)
            Text(
                text = item.title,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                style = textStyle)
        }
    }
}