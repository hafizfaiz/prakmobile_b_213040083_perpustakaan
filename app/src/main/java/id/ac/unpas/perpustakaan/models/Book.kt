package id.ac.unpas.perpustakaan.models

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
@Immutable
data class Book(
    @PrimaryKey val id: String,
    val title: String,
    val author: String,
    val released_date: String,
    val stock: String,

)