package id.ac.unpas.perpustakaan.networks.responses

import id.ac.unpas.perpustakaan.models.BookRequest

data class BookRequestGetResponse(
    val data: List<BookRequest>
)
