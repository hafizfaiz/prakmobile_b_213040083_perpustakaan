package id.ac.unpas.perpustakaan.ui.screens.BookRequestScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import id.ac.unpas.perpustakaan.R
import id.ac.unpas.perpustakaan.models.BookRequest

@Composable
fun BookRequestItem(item: BookRequest, onEditClick: (String) -> Unit, onDeleteClick: (String) -> Unit) {
    Row {
        Text(modifier = Modifier.weight(3f), text = item.library_book_id)
        Text(modifier = Modifier.weight(3f), text = item.start_date)
        Text(modifier = Modifier.weight(3f), text = item.end_date)
        Text(modifier = Modifier.weight(3f), text = item.status)
        Icon(painterResource(id = R.drawable.baseline_edit_24), "Edit", modifier = Modifier.weight(1.5f).clickable {
            onEditClick(item.id)
        })
        Icon(painterResource(id = R.drawable.baseline_delete_24), "Delete", modifier = Modifier.weight(1.5f).clickable {
            onDeleteClick(item.id)
        })
    }
}