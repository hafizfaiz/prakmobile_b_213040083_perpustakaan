package id.ac.unpas.perpustakaan.ui.screens.MembershipScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import id.ac.unpas.perpustakaan.R
import id.ac.unpas.perpustakaan.models.Membership

@Composable
fun MembershipItem(item: Membership, onEditClick: (String) -> Unit, onDeleteClick: (String) -> Unit) {
    Row {
        Text(modifier = Modifier.weight(3f), text = item.name)
        Text(modifier = Modifier.weight(3f), text = item.address)
        Text(modifier = Modifier.weight(3f), text = item.phone)
        Button(modifier = Modifier.weight(1.5f), onClick = { onEditClick(item.id) }) {
            Image(painterResource(id = R.drawable.baseline_edit_24), contentDescription = "Edit")
        }
        Button(modifier = Modifier.weight(1.5f), onClick = { onDeleteClick(item.id) }) {
            Image(painterResource(id = R.drawable.baseline_delete_24), contentDescription = "Delete")
        }
    }
}