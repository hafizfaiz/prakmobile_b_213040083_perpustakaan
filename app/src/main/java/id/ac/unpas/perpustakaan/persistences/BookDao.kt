package id.ac.unpas.perpustakaan.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.unpas.perpustakaan.models.Book

@Dao
interface BookDao {

    @Query("select * from Book")
    fun loadAll(): LiveData<List<Book>>

    @Query("select * from book")
    suspend fun findAll(): List<Book>

    @Query("select * from book where id = :id")
    fun load(id: String): LiveData<Book>

    @Query("select * from book where id = :id")
    suspend fun getById(id: String): Book?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg items: Book)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(list: List<Book>)

    @Query("delete from book where id = :id")
    suspend fun delete(id: String)

    @Query("select * from book where id = :id")
    suspend fun find(id: String): Book?

    @Delete
    suspend fun delete(item: Book)
}