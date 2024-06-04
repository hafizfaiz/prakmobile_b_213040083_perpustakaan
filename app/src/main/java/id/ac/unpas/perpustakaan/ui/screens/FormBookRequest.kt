package id.ac.unpas.perpustakaan.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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

    Text(
        text = "Form Book Request",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
    )

    Column(modifier = modifier.fillMaxWidth()) {

        Column(modifier = Modifier.padding(10.dp).fillMaxWidth()) {

            OutlinedTextField(
                label = { Text(text = "Nama Member") },
                modifier = Modifier.fillMaxWidth(),
                value = library_book_id.value,
                onValueChange = {
                    library_book_id.value = it
                }
            )

            OutlinedTextField(
                label = { Text(text = "Alamat") },
                modifier = Modifier.fillMaxWidth(),
                value = library_member_id.value,
                onValueChange = {
                    start_date.value = it
                }
            )

            OutlinedTextField(
                label = { Text(text = "No Telephone") },
                modifier = Modifier.fillMaxWidth(),
                value = end_date.value,
                onValueChange = {
                    end_date.value = it
                }
            )
            OutlinedTextField(
                label = { Text(text = "No Telephone") },
                modifier = Modifier.fillMaxWidth(),
                value = status.value,
                onValueChange = {
                    status.value = it
                }
            )

            Row {
                Button(modifier = Modifier.weight(5f), onClick = {
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
