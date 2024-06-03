package id.ac.unpas.perpustakaan.networks.responses


import id.ac.unpas.perpustakaan.models.Membership

data class MembershipGetResponse(
    val data: List<Membership>
)

