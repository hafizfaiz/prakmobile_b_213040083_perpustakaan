package id.ac.unpas.perpustakaan.networks


import com.skydoves.sandwich.ApiResponse
import id.ac.unpas.perpustakaan.models.BookRequest
import id.ac.unpas.perpustakaan.networks.responses.BookRequestDeleteResponse
import id.ac.unpas.perpustakaan.networks.responses.BookRequestGetResponse
import id.ac.unpas.perpustakaan.networks.responses.BookRequestPostResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BookRequestApi {
    @GET("book-request")
    suspend fun findAll(): ApiResponse<BookRequestGetResponse>

    @POST("book-request")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body BookRequest : BookRequest): ApiResponse<BookRequestPostResponse>

    @PUT("book-request/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") id: String, @Body BookRequest: BookRequest): ApiResponse<BookRequestPostResponse>

    @DELETE("book-request/{id}")
    suspend fun delete(@Path("id") id: String): ApiResponse<BookRequestDeleteResponse>
}
