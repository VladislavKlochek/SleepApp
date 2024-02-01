import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sleepapp.dao.NotesViewModel
import com.example.sleepapp.notesscreen.NoteItem
import com.example.sleepapp.notesscreen.note
import kotlinx.coroutines.flow.Flow


@Composable
fun NotesScreen(
    activityContext: Context,
    notesViewModel: NotesViewModel
    ) {
    val tempList: Flow<List<note>> = notesViewModel.itemsList

    val notesList: State<List<note>> = tempList.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .padding(5.dp)
    ) {
        itemsIndexed(notesList.value) { _, item ->
            NoteItem(note = item, activityContext, notesViewModel)
        }
    }
}