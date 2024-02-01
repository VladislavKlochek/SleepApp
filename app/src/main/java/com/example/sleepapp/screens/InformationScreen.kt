
import android.content.Context
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import com.example.sleepapp.R
import com.example.sleepapp.informationscreen.Topic
import com.example.sleepapp.informationscreen.TopicItem

@Composable
fun InformationScreen(context: Context) {

    val topics = listOf<Topic>(
        Topic(R.drawable.sleepicon_removebg_preview, "Общая информация", context.getString(R.string.genericInformation)),
        Topic(R.drawable.luciddreamicon_removebg_preview, "Осознанный сон", context.getString(R.string.lucidDream)),
        Topic(R.drawable.scaredcaticon_removebg_preview, "Сонный паралич", context.getString(R.string.sleepParalysis)),
    )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            content = {
                itemsIndexed(topics) { _: Int, item: Topic -> TopicItem(item = item, context) }
            }
        )
}
