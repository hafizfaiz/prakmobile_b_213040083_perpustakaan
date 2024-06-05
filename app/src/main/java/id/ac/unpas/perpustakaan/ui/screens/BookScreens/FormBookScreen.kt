package id.ac.unpas.perpustakaan.ui.screens.BookScreens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.benasher44.uuid.uuid4
import id.ac.unpas.perpustakaan.ui.screens.BookScreens.BookViewModel
import kotlinx.coroutines.launch

@Composable
fun FormBookScreen(modifier: Modifier = Modifier, id: String? = null) {

    val viewModel = hiltViewModel<BookViewModel>()
    val scope = rememberCoroutineScope()

    val title = remember { mutableStateOf(TextFieldValue("")) }
    val author = remember { mutableStateOf(TextFieldValue("")) }
    val released_date = remember { mutableStateOf(TextFieldValue("")) }
    val stock = remember { mutableStateOf(TextFieldValue("")) }

    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogAuthor by remember { mutableStateOf("") }
    var dialogReleasedDate by remember { mutableStateOf("") }
    var dialogStock by remember { mutableStateOf("") }

    Text(
        text = "Perpustakaan",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
    )

    Column(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(10.dp).fillMaxWidth()) {
            OutlinedTextField(
                label = { Text(text = "Nama Buku") },
                modifier = Modifier.fillMaxWidth(),
                value = title.value,
                onValueChange = { title.value = it }
            )

            OutlinedTextField(
                label = { Text(text = "Penulis") },
                modifier = Modifier.fillMaxWidth(),
                value = author.value,
                onValueChange = { author.value = it }
            )

            OutlinedTextField(
                label = { Text(text = "Tahun Rilis") },
                modifier = Modifier.fillMaxWidth(),
                value = released_date.value,
                onValueChange = { released_date.value = it }
            )

            OutlinedTextField(
                label = { Text(text = "Jumlah Stock") },
                modifier = Modifier.fillMaxWidth(),
                value = stock.value,
                onValueChange = { stock.value = it }
            )

            Row {
                Button(modifier = Modifier.weight(5f), onClick = {
                    dialogTitle = title.value.text
                    dialogAuthor = author.value.text
                    dialogReleasedDate = released_date.value.text
                    dialogStock = stock.value.text
                    showDialog = true
                }) {
                    Text(text = "Simpan")
                }
                Button(modifier = Modifier.weight(5f), onClick = {
                    title.value = TextFieldValue("")
                    author.value = TextFieldValue("")
                    released_date.value = TextFieldValue("")
                    stock.value = TextFieldValue("")
                }) {
                    Text(text = "Batal")
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            text = { Text("Data Berhasil Disimpan!") },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    if (id != null) {
                        scope.launch {
                            viewModel.update(id, dialogTitle, dialogAuthor, dialogReleasedDate, dialogStock)
                        }
                    } else {
                        scope.launch {
                            viewModel.insert(uuid4().toString(), dialogTitle, dialogAuthor, dialogReleasedDate, dialogStock)
                        }
                    }
                }) {
                    Text("Ok")
                }
            }
        )
    }

    viewModel.isDone.observe(LocalLifecycleOwner.current) {
        if (it) {
            title.value = TextFieldValue("")
            author.value = TextFieldValue("")
            released_date.value = TextFieldValue("")
            stock.value = TextFieldValue("")
        }
    }

    LaunchedEffect(id) {
        if (id != null) {
            scope.launch {
                viewModel.find(id)
            }
        }
    }

    viewModel.item.observe(LocalLifecycleOwner.current) {
        title.value = TextFieldValue(it.title)
        author.value = TextFieldValue(it.author)
        released_date.value = TextFieldValue(it.released_date)
        stock.value = TextFieldValue(it.stock)
    }
}
