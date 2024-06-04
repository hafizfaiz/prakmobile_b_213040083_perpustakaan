package id.ac.unpas.perpustakaan.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.unpas.perpustakaan.models.BookRequest

@Dao
interface BookRequestDao {


    @Query("SELECT * FROM BookRequest")
    fun getAll(): List<BookRequest>

    @Query("select * from BookRequest")
    fun loadAll(): LiveData<List<BookRequest>>

    @Query("select * from BookRequest")
    suspend fun findAll(): List<BookRequest>

    @Query("select * from BookRequest where id = :id")
    fun load(id: String): LiveData<BookRequest>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg items: BookRequest)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(list: List<BookRequest>)

    @Query("delete from BookRequest where id = :id")
    suspend fun delete(id: String)

    @Query("select * from BookRequest where id = :id")
    suspend fun find(id: String): BookRequest?

    @Delete
    suspend fun delete(item: BookRequest)
    @Insert
    fun insertAll(vararg bookRequests: BookRequest)
}