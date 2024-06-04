package id.ac.unpas.perpustakaan.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.unpas.perpustakaan.models.Book
import id.ac.unpas.perpustakaan.models.BookRequest

@Dao
interface BookRequestDao {

    @Query("SELECT * FROM book_requests")
    fun loadAll(): LiveData<List<BookRequest>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bookRequest: BookRequest)

    @Delete
    suspend fun delete(bookRequest: BookRequest)

    @Query("DELETE FROM book_requests WHERE id = :id")
    suspend fun deleteById(id: String)
}