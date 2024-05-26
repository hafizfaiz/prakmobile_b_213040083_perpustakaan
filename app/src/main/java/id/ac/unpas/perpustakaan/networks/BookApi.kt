package id.ac.unpas.perpustakaan.networks

import com.skydoves.sandwich.ApiResponse
import id.ac.unpas.perpustakaan.models.Book
import id.ac.unpas.perpustakaan.networks.responses.BookGetResponse
import id.ac.unpas.perpustakaan.networks.responses.BookPostResponse
import id.ac.unpas.perpustakaan.networks.responses.BookDeleteResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BookApi {
    @GET("book")
    suspend fun findAll(): ApiResponse<BookGetResponse>

    @POST("book")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body book : Book): ApiResponse<BookPostResponse>

    @PUT("book/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") id: String, @Body book: Book): ApiResponse<BookPostResponse>

    @DELETE("book/{id}")
    suspend fun delete(@Path("id") id: String): ApiResponse<BookDeleteResponse>
}