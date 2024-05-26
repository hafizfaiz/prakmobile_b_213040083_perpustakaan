package id.ac.unpas.perpustakaan.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Member(
    @PrimaryKey val id: Int,
    val name: String,
    val address: String,
    val createdAt: String,
    val updatedAt: String
)