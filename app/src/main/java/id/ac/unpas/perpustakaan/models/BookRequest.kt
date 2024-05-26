package id.ac.unpas.perpustakaan.models

import androidx.room.PrimaryKey

data class BookRequest(
    @PrimaryKey val id: Int,
    val bookId: Int,
    val memberId: Int,
    val startDate: String,
    val endDate: String,
    val status: String,
    val createdAt: String,
    val updatedAt: String
)
