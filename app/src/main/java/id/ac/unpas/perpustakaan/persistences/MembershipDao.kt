package id.ac.unpas.perpustakaan.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.unpas.perpustakaan.models.Membership

@Dao
interface MembershipDao {
    @Query("SELECT * FROM Membership")
    fun loadAll(): LiveData<List<Membership>>

    @Query("SELECT * FROM Membership")
    suspend fun findAll(): List<Membership>

    @Query("SELECT * FROM Membership WHERE id = :id")
    fun load(id: String): LiveData<Membership>

    @Query("SELECT * FROM Membership WHERE id = :id")
    suspend fun getById(id: String): Membership?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg items: Membership)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(list: List<Membership>)

    @Query("DELETE FROM Membership WHERE id = :id")
    suspend fun delete(id: String)

    @Query("SELECT * FROM Membership WHERE id = :id")
    fun find(id: String): LiveData<Membership>

    @Delete
    suspend fun delete(item: Membership)
}
