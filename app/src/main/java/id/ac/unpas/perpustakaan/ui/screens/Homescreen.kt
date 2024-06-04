package id.ac.unpas.perpustakaan.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier, onLihat: () -> Unit) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = "Selamat Datang", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Button(onClick = {
            navController.navigate(NavScreen.Add.route)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Tambah Buku")
        }
        Button(onClick = {
            onLihat()
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Pinjam Buku")
        }
        Button(onClick = {
            navController.navigate(NavScreen.AddBookRequest.route)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Tambah Permintaan Buku")
        }
        Button(onClick = {
            navController.navigate(NavScreen.ListBookRequest.route)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Book Request")
        }
    }
}