package id.ac.unpas.perpustakaan.ui.screens.BookRequestScreens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unpas.perpustakaan.base.LiveCoroutinesViewModel
import id.ac.unpas.perpustakaan.models.BookRequest
import id.ac.unpas.perpustakaan.repositories.BookRequestRepository
import javax.inject.Inject

@HiltViewModel
class BookRequestViewModel @Inject constructor(private val bookRequestRepository: BookRequestRepository) : LiveCoroutinesViewModel() {

    private val _isDone: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDone: LiveData<Boolean> = _isDone

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _item: MutableLiveData<BookRequest> = MutableLiveData()
    val item: LiveData<BookRequest> = _item

    private val _bookRequest: MutableLiveData<Boolean> = MutableLiveData(false)
    val bookRequests: LiveData<List<BookRequest>> = _bookRequest.switchMap {
        _isLoading.postValue(true)
        launchOnViewModelScope {
            bookRequestRepository.loadItems(
                onSuccess = {
                    _isLoading.postValue(false)
                },
                onError = {
                    _isLoading.postValue(false)
                    Log.e("BookViewModel", it)
                }
            ).asLiveData()
        }
    }

    suspend fun insert(
        id: String,
        library_book_id: String,
        library_member_id: String,
        start_date: String,
        end_date: String,
        status: String,
    ) {
        _isLoading.postValue(true)
        bookRequestRepository.insert(BookRequest(id, library_book_id, library_member_id, start_date, end_date, status),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _bookRequest.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _bookRequest.postValue(true)
            }
        )
    }

    suspend fun update(
        id: String,
        library_book_id: String,
        library_member_id: String,
        start_date: String,
        end_date: String,
        status: String,
    ) {
        _isLoading.postValue(true)
        bookRequestRepository.update(BookRequest(id, library_book_id, library_member_id, start_date, end_date, status),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _bookRequest.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _bookRequest.postValue(true)
            }
        )
    }

    suspend fun delete(id: String) {
        _isLoading.postValue(true)
        bookRequestRepository.delete(id,
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _bookRequest.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _bookRequest.postValue(true)
            }
        )
    }

    suspend fun find(id: String) {
        val book = bookRequestRepository.find(id)
        book?.let {
            _item.postValue(it)
        }
    }
}