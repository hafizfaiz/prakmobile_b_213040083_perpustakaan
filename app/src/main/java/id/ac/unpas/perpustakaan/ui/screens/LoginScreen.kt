package id.ac.unpas.perpustakaan.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

@Composable
fun LoginScreen(modifier: Modifier = Modifier, onLoginClick: () -> Unit,
                onRegisterClick: () -> Unit) {
    val loginMessage = remember { mutableStateOf(TextFieldValue("")) }
    val username = remember { mutableStateOf(TextFieldValue("")) }
    val password = remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = modifier.fillMaxWidth()) {
        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()) {
            OutlinedTextField(
                label = { Text(text = "Username") },
                modifier = Modifier.fillMaxWidth(),
                value = username.value,
                onValueChange = {
                    username.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                value = password.value,
                onValueChange = {
                    password.value = it
                })

            Row {
                Button(modifier = Modifier.weight(5f), onClick = {
                    if (username.value.text == "admin" && password.value.text == "12345") {
                        onLoginClick()
                    } else {
                        loginMessage.value = TextFieldValue("• Login gagal, mohon periksa kembali username dan password")
                    }
                }) {
                    Text(text = "Login")
                }

                Button(modifier = Modifier.weight(5f), onClick = {
                    onRegisterClick() // Panggil onRegisterClick saat tombol diklik
                }) {
                    Text(text = "Daftar")
                }

            }

            if (loginMessage.value.text.isNotEmpty()) {
                Text(
                    text = loginMessage.value.text,
                    color = Color.Red
                )
            }
        }
    }
}
