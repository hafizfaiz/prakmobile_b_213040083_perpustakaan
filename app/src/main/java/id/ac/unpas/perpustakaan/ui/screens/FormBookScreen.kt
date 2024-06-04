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
fun FormBookScreen(modifier: Modifier = Modifier, id : String? = null) {

    val viewModel = hiltViewModel<BookViewModel>()
    val scope = rememberCoroutineScope()

    val title = remember { mutableStateOf(TextFieldValue("")) }
    val author = remember { mutableStateOf(TextFieldValue("")) }
    val released_date = remember { mutableStateOf(TextFieldValue("")) }
    val stock = remember { mutableStateOf(TextFieldValue("")) }

    Text(
        text = "Perpustakaan",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
    )

    Column(modifier = modifier
        .fillMaxWidth()) {


        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()) {

            OutlinedTextField(
                label = { Text(text = "Nama Buku") },
                modifier = Modifier.fillMaxWidth(),
                value = title.value,
                onValueChange = {
                    title.value = it
                }
            )

            OutlinedTextField(
                label = { Text(text = "Penulis") },
                modifier = Modifier.fillMaxWidth(),
                value = author.value,
                onValueChange = {
                    author.value = it
                }
            )

            OutlinedTextField(
                label = { Text(text = "Tahun Rilis") },
                modifier = Modifier.fillMaxWidth(),
                value = released_date.value,
                onValueChange = {
                    released_date.value = it
                }
            )

            OutlinedTextField(
                label = { Text(text = "Jumlah Stock") },
                modifier = Modifier.fillMaxWidth(),
                value = stock.value,
                onValueChange = {
                    stock.value = it
                }
            )


            Row {
                Button(modifier = Modifier.weight(5f), onClick = {
                    if (id != null) {
                        scope.launch {
                            viewModel.update(id, title.value.text, author.value.text, released_date.value.text, stock.value.text)
                        }
                    } else {
                        scope.launch {
                            viewModel.insert(uuid4().toString(), title.value.text, author.value.text, released_date.value.text, stock.value.text)
                        }
                    }
                }) {
                    Text(text = "Simpan")
                }
                Button(modifier = Modifier.weight(5f),
                    onClick = {
                        title.value = TextFieldValue("")
                        author.value = TextFieldValue("")
                        released_date.value = TextFieldValue("")
                        stock.value = TextFieldValue("")
                    }
                ) {
                    Text(text = "Batal")
                }
            }
            }
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

    //menampilkan data ketika di edit
        viewModel.item.observe(LocalLifecycleOwner.current) {
            title.value = TextFieldValue(it.title)
            author.value = TextFieldValue(it.author)
            released_date.value = TextFieldValue(it.released_date)
            stock.value = TextFieldValue(it.stock)
        }
    }