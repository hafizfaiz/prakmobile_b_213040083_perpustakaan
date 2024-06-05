package id.ac.unpas.perpustakaan.ui.screens.MembershipScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import id.ac.unpas.perpustakaan.models.Membership
import kotlinx.coroutines.launch

@Composable
fun ListMembershipScreen(modifier: Modifier = Modifier, onClick: (String) -> Unit) {

    val scope = rememberCoroutineScope()
    val viewModel = hiltViewModel<MemberViewModel>()

    val list: List<Membership> by viewModel.memberships.observeAsState(listOf())
    val title = remember { mutableStateOf("Membership") }
    val openDialog = remember {
        mutableStateOf(false)
    }
    val activeId = remember {
        mutableStateOf("")
    }
    val deleting = remember {
        mutableStateOf(false)
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = title.value, modifier = Modifier.fillMaxWidth())
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(list.size) { index ->
                val item = list[index]
                MembershipItem(item = item, onEditClick = { id ->
                    onClick(id)
                }, onDeleteClick = { id ->
                    deleting.value = true
                    activeId.value = id
                    openDialog.value = true
                })
            }
        }
    }

    if (openDialog.value) {
        AlertDialog(onDismissRequest = {
            openDialog.value = false },
            title = {
                Text(text = "Konfirmasi")
            },
            text = {
                Text(text = "Apakah anda yakin ingin menghapus data ini?")
            },
            confirmButton = { Button(onClick = {
                scope.launch {
                    viewModel.delete(activeId.value)
                }
                openDialog.value = false
            }) {
                Text(text = "Ya")
            }
            },
            dismissButton = {
                Button(onClick = {
                    openDialog.value = false
                }) {
                    Text(text = "Tidak")
                }
            }
        )
    }

    viewModel.isLoading.observe(LocalLifecycleOwner.current) {
        title.value = if (it) "Loading..." else "Membership"
    }
}
