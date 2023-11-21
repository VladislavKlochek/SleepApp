import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumTopAppBar
import java.text.SimpleDateFormat
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sleepapp.dao.NotesViewModel
import com.example.sleepapp.notesscreen.NoteItem
import com.example.sleepapp.notesscreen.note
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    activityContext: Context,
    notesViewModel: NotesViewModel
    ) {
    val notesList = notesViewModel.itemsList.collectAsState(initial = emptyList())
    /*val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT)*/
    /*val items = listOf<note>(
        note(
            dateFormat.parse("27.08.2021"),
            "Лес",
            "«Все из ничего» — название книги интригует. И это не просто парадоксальная фраза. Лоуренс Краусс, блестящий популяризатор научного знания, задался целью рассказать, как современная наука объясняет появление нашего мира — начиная с элементарных частиц и заканчивая Вселенной в целом. Ричард Докинз высказался о книге Краусса так: «Не исключено, что по своему влиянию на доктрину о высшем разуме это самая значительная научная книга после «Происхождения видов» Дарвина».\n" +
                    "\n" +
                    "Эту книгу не очень просто читать. Автор явно рассчитывает на то, что у читателя есть базовые знания о микро- и макромире. Но на помощь приходят многочисленные иллюстрации и умение Лоуренса Краусса говорить просто о сложном. И это очень важно, потому что многие идеи книги поражают и с трудом укладываются в голове. Например, автор вслед за Ричардом Фейнманом утверждает, что движение электрона со сверхсветовой скоростью теоретически не запрещено, но в этом случае он будет двигаться назад по времени. Математически это эквивалентно движению позитрона по той же траектории, но вперед по времени. И тут автор с помощью простых диаграмм показывает, как такая ситуация приводит к возникновению нескольких элементарных частиц буквально «из ничего». В общем, эта книга для тех, кто готов к трудностям и неожиданностям на пути постижения природы.",
            arrayOf("#страшно", "странно", "хорошо", "зловеще"),
            coffee = false,
            alcohol = false
        ),
        note(
            dateFormat.parse("22.10.2022"),
            "Ночь",
            "«Эгоистичный ген» — классика научно-популярной литературы. С ней должен быть знаком каждый культурный человек. Как и «Войну и мир», вы должны хотя бы начать ее читать \uD83D\uDE09  Первоначально книга была задумана как оригинальный способ научно-популярного рассказа о биологической эволюции. Ричард Докинз заявил, что именно гены подвергаются естественному отбору, а не виды и не отдельные организмы. \n" +
                    "\n" +
                    "«Эгоистические интересы» генов послужили автору отличной научной метафорой. Используя ее, он смог популярно объяснить многие странности эволюции, даже возникновение в живой природе альтруизма и самопожертвования. Метафора оказалась настолько удачной, что ее взяли на вооружение многие профессиональные биологи — научно-популярная книга дала толчок развитию науки. И в этом ее уникальность.\n" +
                    "\n" +
                    "Кстати, на страницах этой книги впервые появился термин «мем». Ричард Докинз придумал это слово для обозначения единицы культурной информации, то есть информации, передающейся негенетическим путем. А теперь это едва ли не самое модное слово в сети.",
            arrayOf("#страшно", "#непонятно", "#плохо"),
            coffee = true,
            alcohol = false
        ),
        note(
            dateFormat.parse("2.13.2020"),
            "Луга",
            "Это редкая книга. Ее автор — физик-теоретик, который одним из первых наблюдал удивительное явление — квантовую нелокальность. Он поставил эксперимент, основываясь на работах Бора и Гейзенберга, Эйнштейна и Белла. Жизан — один из тех, кто сегодня совершает «вторую квантовую революцию». Он отлично понимает, что вещи, о которых он говорит, трудно представить нормальному человеку. И Жизан тратит много сил, чтобы растолковать «квантовые чудеса», которые он сам совершал.\n" +
                    "\n" +
                    "В классическом мире нет аналогов квантовой нелокальности. Мы точно знаем: чтобы воздействовать на вещь, к ней надо прикоснуться. Необязательно рукой. Можно передать сигнал, например по интернету. Сигнал достигнет вещи со скоростью, не превышающей скорость света, и совершит нужное действие. Но траектория сигнала всегда есть, и она всегда непрерывна. В квантовом мире все не так. Там один объект может мгновенно «взаимодействовать» с другим, «не касаясь» его и не прочерчивая непрерывную траекторию. Представить это трудно. Но это так. Жизан это твердо знает. В 1995 году он установил квантовое нелокальное «взаимодействие» между частицами на расстоянии 23 километра.\n" +
                    "\n" +
                    "В книге нет формул. И это принципиальная позиция автора. Он говорит, что его цель — рассказать о физических идеях, а не забросать читателя математикой. Но в этой небольшой книге много метафор. Видимо, иначе и нельзя рассказать о «чудесном» квантовом мире. Это трудная книга, но разговор с современным «волшебником», безусловно, стоит потраченного времени.",
            arrayOf("#страшно", "#непонятно", "#плохо"),
            coffee = true,
            alcohol = true
        ),
        note(
            dateFormat.parse("2.13.2020"),
            "Ненадо besi be fbw evfw ДЯДЯ",
            "Если вы решите подойти к серьезному ученому и попросите рассказать, как устроена Вселенная, «только покороче», то серьезный ученый проигнорирует вопрос — и будет прав. Но Стивен Хокинг —  звезда космологии, не нуждающаяся в представлениях — дерзнул придумать такой рассказ. И вышло действительно коротко: всего на 160 страницах у вас на глазах возникнет Вселенная, пространство, время и...  множество вопросов, которые растут, как головы Лернейской гидры. Ученые тратят столетия, отвечая на один, чтобы на его месте тут же появился еще десяток. \n" +
                    "\n" +
                    "Книга станет кратким введением в космологию, простым ответом на вопрос, «как родилась Вселенная с точки зрения современной науки». Его простота, естественно, обманчива, но у читателя останется выбор: удовлетвориться этими ответами, испугавшись погружения в фундаментальную науку, или пойти дальше, имея на руках путеводную карту от Стивена Хокинга. \n" +
                    "\n" +
                    "К вопросу о существовании теории всего Хокинг подходит только в самом конце — чтобы оставить его открытым как обозначение цели, к которой может прийти наука в ближайшее столетие. \n" +
                    "\n" +
                    "Анастасия Кожара, эксперт Всенауки\n" +
                    "\n" +
                    "Издательство: АСТ\n" +
                    "Книги о Вселенной и материи\n" +
                    "Скачать: fb2 epub mobi pdf\n" +
                    "Читать: НЭБ Свет\n" +
                    "\n" +
                    "Подробнее\n" +
                    "Норман Дойдж\n" +
                    "Пластичность мозга\n" +
                    "Автор этой книги — психиатр и психоаналитик. Нейропластичность — это способность мозга восстанавливать нормальную работу даже при сильных расстройствах, например при инсульте. Работающие нейронные сети могут адаптироваться к новым, не свойственным им задачам. И добиться такой адаптации можно тренировкой и обучением.\n" +
                    "\n" +
                    "Дойдж приводит примеры, которые, по его словам, похожи на «нейропластическое чудо». У женщины отказал вестибулярный аппарат. Но его удалось восстановить, стимулируя язык слабыми разрядами тока. После удаления одного из полушарий мозга другое почти полностью берет на себя утраченные функции. И человек живет нормальной жизнью. Таких примеров много, и они впечатляют.\n" +
                    "\n" +
                    "Дойдж пересказывает статью нейробиолога Пола Бач-и-Рита, в которой описано удивительное устройство. Оно работает так. Камера принимает «картинку», компьютер преобразует ее сигнал в удары массива молоточков, они отстукивают по спине слепого человека изображение. И он видит. Дойдж пишет, это устройство называлось «тактильно-зрительным аппаратом» и позволяло слепым людям различать линии, оценивать, какие объекты находятся ближе, а какие дальше. И даже узнавать предметы — телефон или чашку.\n" +
                    "\n" +
                    "Автор книги делает акцент не на отдельных моментах работы мозга, а на его целостности. Мозг представляет собой единый орган, а не просто набор специализированных блоков. И это дает ему широкие возможности для восстановления. Книга написана увлекательно и рассчитана на самый широкий круг читателей.\n" +
                    "\n" +
                    "Владимир Губайловский, эксперт Всенауки, научный журналист, писатель\n" +
                    "\n" +
                    "Издательство: Бомбора\n" +
                    "Книги о мышлении и познании\n" +
                    "Скачать: fb2 epub mobi pdf\n" +
                    "\n" +
                    "Подробнее\n" +
                    "Николя Жизан\n" +
                    "Квантовая случайность. Нелокальность, телепортация и другие квантовые чудеса\n" +
                    "Это редкая книга. Ее автор — физик-теоретик, который одним из первых наблюдал удивительное явление — квантовую нелокальность. Он поставил эксперимент, основываясь на работах Бора и Гейзенберга, Эйнштейна и Белла. Жизан — один из тех, кто сегодня совершает «вторую квантовую революцию». Он отлично понимает, что вещи, о которых он говорит, трудно представить нормальному человеку. И Жизан тратит много сил, чтобы растолковать «квантовые чудеса», которые он сам совершал.\n" +
                    "\n" +
                    "В классическом мире нет аналогов квантовой нелокальности. Мы точно знаем: чтобы воздействовать на вещь, к ней надо прикоснуться. Необязательно рукой. Можно передать сигнал, например по интернету. Сигнал достигнет вещи со скоростью, не превышающей скорость света, и совершит нужное действие. Но траектория сигнала всегда есть, и она всегда непрерывна. В квантовом мире все не так. Там один объект может мгновенно «взаимодействовать» с другим, «не касаясь» его и не прочерчивая непрерывную траекторию. Представить это трудно. Но это так. Жизан это твердо знает. В 1995 году он установил квантовое нелокальное «взаимодействие» между частицами на расстоянии 23 километра.\n" +
                    "\n" +
                    "В книге нет формул. И это принципиальная позиция автора. Он говорит, что его цель — рассказать о физических идеях, а не забросать читателя математикой. Но в этой небольшой книге много метафор. Видимо, иначе и нельзя рассказать о «чудесном» квантовом мире. Это трудная книга, но разговор с современным «волшебником», безусловно, стоит потраченного времени.",
            arrayOf("#страшно", "#непонятно", "#плохо"),
            coffee = false,
            alcohol = false
        )
    )*/


    LazyColumn(
        modifier = Modifier
            .padding(5.dp)
    ) {
        itemsIndexed(notesList.value) { _, item ->
            NoteItem(note = item, activityContext, item.id)
        }
    }


}