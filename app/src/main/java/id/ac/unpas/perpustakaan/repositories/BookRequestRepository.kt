package id.ac.unpas.perpustakaan.repositories


import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import id.ac.unpas.perpustakaan.models.Book
import id.ac.unpas.perpustakaan.models.BookRequest
import id.ac.unpas.perpustakaan.networks.BookApi
import id.ac.unpas.perpustakaan.persistences.BookDao
import id.ac.unpas.perpustakaan.persistences.BookRequestDao
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


data class BookRequestRepository @Inject constructor(
private val bookRequestDao: BookRequestDao
) {

    fun loadAllRequests(): LiveData<List<BookRequest>> = bookRequestDao.loadAll()

    suspend fun insertRequest(bookRequest: BookRequest) {
        bookRequestDao.insert(bookRequest)
    }

    suspend fun deleteRequest(bookRequest: BookRequest) {
        bookRequestDao.delete(bookRequest)
    }

    suspend fun deleteRequestById(id: String) {
        bookRequestDao.deleteById(id)
    }
}