package id.ac.unpas.perpustakaan.networks.responses

import id.ac.unpas.perpustakaan.models.BookRequest


data class BookRequestPostResponse(
    val message: String,
    val success: Boolean,
    val data: BookRequest?
)
