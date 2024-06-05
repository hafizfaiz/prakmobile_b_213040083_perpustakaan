package id.ac.unpas.perpustakaan.ui.screens.BookScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.ac.unpas.perpustakaan.models.Book
import id.ac.unpas.perpustakaan.ui.screens.BookScreens.BookItem
import id.ac.unpas.perpustakaan.ui.screens.BookScreens.BookViewModel
import kotlinx.coroutines.launch

@Composable
fun ListBookScreen(modifier: Modifier = Modifier, onClick: (String) -> Unit) {

    val scope = rememberCoroutineScope()
    val viewModel = hiltViewModel<BookViewModel>()

    val list: List<Book> by viewModel.books.observeAsState(listOf())
    val title = remember { mutableStateOf("BOOK") }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = title.value, modifier = Modifier.fillMaxWidth())
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            items(list.size) { index ->
                val item = list[index]
                BookItem(item = item, onEditClick = { id ->
                    onClick(id)
                }, onDeleteClick = { id ->
                    scope.launch {
                        viewModel.delete(id)
                    }
                })
                Divider()
            }
        }
    }

    viewModel.isLoading.observe(LocalLifecycleOwner.current) {
        if (it) {
            title.value = "Loading..."
        } else {
            title.value = " "
        }
    }
}
