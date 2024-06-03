package id.ac.unpas.perpustakaan.networks

import androidx.lifecycle.LiveData
import com.skydoves.sandwich.ApiResponse
import id.ac.unpas.perpustakaan.models.Membership
import id.ac.unpas.perpustakaan.networks.responses.MembershipDeleteResponse
import id.ac.unpas.perpustakaan.networks.responses.MembershipGetResponse
import id.ac.unpas.perpustakaan.networks.responses.MembershipPostResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MembershipApi {
    @GET("memberships")
    suspend fun findAll(): ApiResponse<MembershipGetResponse>

    @POST("memberships")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body membership: Membership): ApiResponse<MembershipPostResponse>

    @PUT("memberships/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") id: String, @Body membership: Membership): ApiResponse<MembershipPostResponse>

    @DELETE("memberships/{id}")
    suspend fun delete(@Path("id") id: String): ApiResponse<MembershipDeleteResponse>

    fun getMembershipValue(membershipLiveData: LiveData<Membership>): Membership? {
        return membershipLiveData.value
    }
}
