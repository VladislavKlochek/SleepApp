
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sleepapp.R
import com.example.sleepapp.informationscreen.Topic
import com.example.sleepapp.informationscreen.TopicItem

@Composable
fun InformationScreen() {

    val topics = listOf<Topic>(
        Topic(R.drawable.sleepicon_removebg_preview, "Общая информация"),
        Topic(R.drawable.luciddreamicon_removebg_preview, "Осознанный сон"),
        Topic(R.drawable.scaredcaticon_removebg_preview, "Сонный паралич"),
        Topic(R.drawable.sleepicon_removebg_preview, "Общая информация"),
        Topic(R.drawable.luciddreamicon_removebg_preview, "Осознанный сон"),
        Topic(R.drawable.scaredcaticon_removebg_preview, "Сонный паралич"),
        Topic(R.drawable.sleepicon_removebg_preview, "Общая информация"),
        Topic(R.drawable.luciddreamicon_removebg_preview, "Осознанный сон"),
        Topic(R.drawable.scaredcaticon_removebg_preview, "Сонный паралич"),
        Topic(R.drawable.sleepicon_removebg_preview, "Общая информация"),
        Topic(R.drawable.luciddreamicon_removebg_preview, "Осознанный сон"),
        Topic(R.drawable.scaredcaticon_removebg_preview, "Сонный паралич"),
    )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            content = {
                itemsIndexed(topics) { index: Int, item: Topic -> TopicItem(item = item) }

            }
        )

}
