package id.ac.unpas.perpustakaan.networks.responses

import id.ac.unpas.perpustakaan.models.Membership


data class MembershipPostResponse(
    val message: String,
    val success: Boolean,
    val data: Membership?
)
