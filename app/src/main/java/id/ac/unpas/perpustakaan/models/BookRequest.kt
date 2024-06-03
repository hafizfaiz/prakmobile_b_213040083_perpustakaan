package id.ac.unpas.perpustakaan.models

import androidx.room.PrimaryKey

data class BookRequest(
    @PrimaryKey val id: Int,
    val library_book_id: Int,
    val library_member_id: Int,
    val start_date: String,
    val end_date: String,
    val status: String

)
