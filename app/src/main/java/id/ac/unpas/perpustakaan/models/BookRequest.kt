package id.ac.unpas.perpustakaan.models

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity
@Immutable
data class BookRequest(
        @PrimaryKey val id: String,
        val bookId: String,
        val memberId: String,
        val requestDate: Long
    )

