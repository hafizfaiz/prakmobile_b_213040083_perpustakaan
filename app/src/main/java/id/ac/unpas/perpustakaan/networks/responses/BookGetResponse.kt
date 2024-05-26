package id.ac.unpas.perpustakaan.networks.responses

import id.ac.unpas.perpustakaan.models.Book

data class BookGetResponse(
    val data: List<Book>
)
