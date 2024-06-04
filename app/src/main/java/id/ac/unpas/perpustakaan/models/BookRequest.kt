package id.ac.unpas.perpustakaan.models

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@Immutable
data class BookRequest(
        @PrimaryKey val id: String,
        val library_book_id: String,
        val library_member_id: String,
        val start_date: String,
        val end_date: String,
        val status: String,
)