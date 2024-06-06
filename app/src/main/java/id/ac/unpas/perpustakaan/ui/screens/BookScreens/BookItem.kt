package id.ac.unpas.perpustakaan.ui.screens.BookScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import id.ac.unpas.perpustakaan.R
import id.ac.unpas.perpustakaan.models.Book

@Composable
fun BookItem(item: Book, onEditClick: (String) -> Unit, onDeleteClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(15.dp))
    ) {
        Row (modifier = Modifier.padding(7.dp), verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(id = R.drawable.baseline_book_24),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Column(modifier = Modifier.weight(0.5f)) {
                Text(text = "Buku")
                Text(text = "Penulis")
                Text(text = "Tahun")
                Text(text = "Stock")
            }
            Column(modifier = Modifier.weight(0.1f)) {
                Text(text = ":")
                Text(text = ":")
                Text(text = ":")
                Text(text = ":")
            }
            Column(modifier = Modifier.weight(0.5f)) {
                Text(text = item.title)
                Text(text = item.author)
                Text(text = item.released_date)
                Text(text = item.stock)
            }
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp)
            ) {
                Icon(
                    painterResource(id = R.drawable.baseline_edit_24),
                    "Edit",
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            onEditClick(item.id)
                        })
                Icon(
                    painterResource(id = R.drawable.baseline_delete_24),
                    "Delete",
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            onDeleteClick(item.id)
                        })
            }
        }
    }
//    Row {
//        Text(modifier = Modifier.weight(3f), text = item.title)
//        Text(modifier = Modifier.weight(3f), text = item.author)
//        Text(modifier = Modifier.weight(3f), text = item.released_date)
//        Text(modifier = Modifier.weight(3f), text = item.stock)
//        Icon(painterResource(id = R.drawable.baseline_edit_24), "Edit", modifier = Modifier
//            .weight(1.5f)
//            .clickable {
//                onEditClick(item.id)
//            })
//        Icon(painterResource(id = R.drawable.baseline_delete_24), "Delete", modifier = Modifier
//            .weight(1.5f)
//            .clickable {
//                onDeleteClick(item.id)
//            })
//    }
}