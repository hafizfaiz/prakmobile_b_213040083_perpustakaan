package id.ac.unpas.perpustakaan.ui.screens.BookRequestScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import id.ac.unpas.perpustakaan.R
import id.ac.unpas.perpustakaan.models.BookRequest

@Composable
fun BookRequestItem(item: BookRequest, onEditClick: (String) -> Unit, onDeleteClick: (String) -> Unit) {
    Box(
        modifier = Modifier.padding(8.dp).border(1.dp, Color.Black, shape = RoundedCornerShape(15.dp))
    ) {
        Row (modifier = Modifier.padding(7.dp)){
            Column(modifier = Modifier.weight(0.8f)) {
                Text(text = "Nama Buku")
                Text(text = "Tanggal Dipinjam")
                Text(text = "Tanggal Pengembalian")
                Text(text = "Status")
            }
            Column(modifier = Modifier.weight(0.1f)) {
                Text(text = ":")
                Text(text = ":")
                Text(text = ":")
                Text(text = ":")
            }
            Column(modifier = Modifier.weight(0.5f)) {
                Text(text = item.library_book_id)
                Text(text = item.start_date)
                Text(text = item.end_date)
                Text(text = item.status)
            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp)
            ) {
                Icon(
                    painterResource(id = R.drawable.baseline_edit_24),
                    "Edit",
                    modifier = Modifier
                        .clickable {
                            onEditClick(item.id)
                        })
                Icon(
                    painterResource(id = R.drawable.baseline_delete_24),
                    "Delete",
                    modifier = Modifier
                        .clickable {
                            onDeleteClick(item.id)
                        })
            }
        }
    }
}