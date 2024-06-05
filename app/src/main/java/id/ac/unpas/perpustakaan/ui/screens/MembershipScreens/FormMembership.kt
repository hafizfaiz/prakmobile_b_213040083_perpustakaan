package id.ac.unpas.perpustakaan.ui.screens.MembershipScreens

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
import kotlinx.coroutines.launch

@Composable
fun FormMembership(modifier: Modifier = Modifier, id: String? = null) {

    val viewModel = hiltViewModel<MemberViewModel>()
    val scope = rememberCoroutineScope()

    val name = remember { mutableStateOf(TextFieldValue("")) }
    val address = remember { mutableStateOf(TextFieldValue("")) }
    val phone = remember { mutableStateOf(TextFieldValue("")) }
    var showDialog by remember { mutableStateOf(false) }

    Text(
        text = "Form Membership",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
    )

    Column(modifier = modifier.fillMaxWidth()) {

        Column(modifier = Modifier.padding(10.dp).fillMaxWidth()) {

            OutlinedTextField(
                label = { Text(text = "Nama Member") },
                modifier = Modifier.fillMaxWidth(),
                value = name.value,
                onValueChange = {
                    name.value = it
                }
            )

            OutlinedTextField(
                label = { Text(text = "Alamat") },
                modifier = Modifier.fillMaxWidth(),
                value = address.value,
                onValueChange = {
                    address.value = it
                }
            )

            OutlinedTextField(
                label = { Text(text = "No Telephone") },
                modifier = Modifier.fillMaxWidth(),
                value = phone.value,
                onValueChange = {
                    phone.value = it
                }
            )

            Row {
                Button(modifier = Modifier.weight(5f), onClick = {
                    showDialog = true
                }) {
                    Text(text = "Simpan")
                }
                Button(modifier = Modifier.weight(5f), onClick = {
                    name.value = TextFieldValue("")
                    address.value = TextFieldValue("")
                    phone.value = TextFieldValue("")
                }) {
                    Text(text = "Batal")
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            text = { Text(text = "Data Berhasil Disimpan!") },
            confirmButton = {
                Button(onClick = {
                    if (id != null) {
                        scope.launch {
                            viewModel.update(id, name.value.text, address.value.text, phone.value.text)
                        }
                    } else {
                        scope.launch {
                            viewModel.insert(uuid4().toString(), name.value.text, address.value.text, phone.value.text)
                        }
                    }
                    showDialog = false
                }) {
                    Text("Ok")
                }
            }
        )
    }

    viewModel.isDone.observe(LocalLifecycleOwner.current) {
        if (it) {
            name.value = TextFieldValue("")
            address.value = TextFieldValue("")
            phone.value = TextFieldValue("")
        }
    }

    LaunchedEffect(id) {
        if (id != null) {
            viewModel.find(id)
        }
    }

    viewModel.item.observe(LocalLifecycleOwner.current) {
        name.value = TextFieldValue(it.name)
        address.value = TextFieldValue(it.address)
        phone.value = TextFieldValue(it.phone)
    }
}
