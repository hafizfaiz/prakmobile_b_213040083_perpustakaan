package id.ac.unpas.perpustakaan.ui.screens.BookRequestScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import id.ac.unpas.perpustakaan.ui.screens.BookRequestScreens.BookRequestViewModel
import kotlinx.coroutines.launch

@Composable
fun FormBookRequest(modifier: Modifier = Modifier, id: String? = null) {

    val viewModel = hiltViewModel<BookRequestViewModel>()
    val scope = rememberCoroutineScope()

    val library_book_id = remember { mutableStateOf(TextFieldValue("")) }
    val library_member_id = remember { mutableStateOf(TextFieldValue("")) }
    val start_date = remember { mutableStateOf(TextFieldValue("")) }
    val end_date = remember { mutableStateOf(TextFieldValue("")) }
    val status = remember { mutableStateOf(TextFieldValue("")) }
    var showDialog by remember { mutableStateOf(false) }

    Text(
        text = "Form Book Request",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
    )

    Column(modifier = modifier.fillMaxWidth()) {

        Column(modifier = Modifier.padding(10.dp).fillMaxWidth()) {

            OutlinedTextField(
                label = { Text(text = "Nama Buku") },
                modifier = Modifier.fillMaxWidth(),
                value = library_book_id.value,
                onValueChange = {
                    library_book_id.value = it
                }
            )

            OutlinedTextField(
                label = { Text(text = "Tanggal dipinjam") },
                modifier = Modifier.fillMaxWidth(),
                value = start_date.value,
                onValueChange = {
                    start_date.value = it
                }
            )

            OutlinedTextField(
                label = { Text(text = "Tanggal Pengembalian") },
                modifier = Modifier.fillMaxWidth(),
                value = end_date.value,
                onValueChange = {
                    end_date.value = it
                }
            )
            OutlinedTextField(
                label = { Text(text = "Status") },
                modifier = Modifier.fillMaxWidth(),
                value = status.value,
                onValueChange = {
                    status.value = it
                }
            )

            Row {
                Button(modifier = Modifier.weight(5f), onClick = {
                    showDialog = true
                }) {
                    Text(text = "Simpan")
                }
                Button(modifier = Modifier.weight(5f), onClick = {
                    library_book_id.value = TextFieldValue("")
                    library_member_id.value = TextFieldValue("")
                    start_date.value = TextFieldValue("")
                    end_date.value = TextFieldValue("")
                    status.value = TextFieldValue("")
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
                Button(onClick = {
                    showDialog = false
                    if (id != null) {
                        scope.launch {
                            viewModel.update(id, library_book_id.value.text, library_member_id.value.text, start_date.value.text, end_date.value.text, status.value.text)
                        }
                    } else {
                        scope.launch {
                            viewModel.insert(uuid4().toString(), library_book_id.value.text, library_member_id.value.text, start_date.value.text, end_date.value.text, status.value.text)
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
            library_book_id.value = TextFieldValue("")
            library_member_id.value = TextFieldValue("")
            start_date.value = TextFieldValue("")
            end_date.value = TextFieldValue("")
            status.value = TextFieldValue("")
        }
    }

    LaunchedEffect(id) {
        if (id != null) {
            viewModel.find(id)
        }
    }

    viewModel.item.observe(LocalLifecycleOwner.current) {
        library_book_id.value = TextFieldValue(it.library_book_id)
        library_member_id.value = TextFieldValue(it.library_member_id)
        start_date.value = TextFieldValue(it.start_date)
        end_date.value = TextFieldValue(it.end_date)
        status.value = TextFieldValue(it.status)
    }
}
