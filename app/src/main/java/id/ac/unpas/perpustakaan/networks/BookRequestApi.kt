package id.ac.unpas.perpustakaan.networks


import id.ac.unpas.perpustakaan.models.BookRequest
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface BookRequestApi {
    @GET("bookrequest/findall")
    suspend fun findAll(): List<BookRequest>

    @POST("bookrequest/insert")
    suspend fun insert(bookRequest: BookRequest): Response<BookRequest>

    @PUT("bookrequest/update")
    suspend fun update(bookRequest: BookRequest): Response<BookRequest>

    @DELETE("bookrequest/delete")
    suspend fun delete(bookRequestId: String): Response<Unit>
}
