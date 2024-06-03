package id.ac.unpas.perpustakaan.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Membership(
    @PrimaryKey val id: String,
    val name: String,
    val address: String,
    val phone: String
)