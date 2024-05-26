package id.ac.unpas.perpustakaan.networks.responses

import id.ac.unpas.perpustakaan.models.Book


data class BookPostResponse(
    val message: String,
    val success: Boolean,
    val data: Book?
)
