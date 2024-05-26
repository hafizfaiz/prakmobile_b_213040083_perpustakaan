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
    val editionReleaseTimeYear: String,
    val stock: String,
    val createdAt: String,
    val updatedAt: String,
    val bookRequestId: String // Foreign key ke BookRequest
)