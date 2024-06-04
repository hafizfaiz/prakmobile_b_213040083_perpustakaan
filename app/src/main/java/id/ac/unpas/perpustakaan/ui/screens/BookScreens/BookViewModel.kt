package id.ac.unpas.perpustakaan.ui.screens.BookScreens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unpas.perpustakaan.base.LiveCoroutinesViewModel
import id.ac.unpas.perpustakaan.models.Book
import id.ac.unpas.perpustakaan.repositories.BookRepository
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(private val bookRepository: BookRepository) : LiveCoroutinesViewModel() {

    private val _isDone: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDone: LiveData<Boolean> = _isDone

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _item: MutableLiveData<Book> = MutableLiveData()
    val item: LiveData<Book> = _item

    private val _book: MutableLiveData<Boolean> = MutableLiveData(false)
    val books: LiveData<List<Book>> = _book.switchMap {
        _isLoading.postValue(true)
        launchOnViewModelScope {
            bookRepository.loadItems(
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
        title: String,
        author: String,
        released_date: String,
        stock: String
    ) {
        _isLoading.postValue(true)
        bookRepository.insert(Book(id, title, author, released_date, stock),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _book.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _book.postValue(true)
            }
        )
    }

    suspend fun update(
        id: String,
        title: String,
        author: String,
        released_date: String,
        stock: String
    ) {
        _isLoading.postValue(true)
        bookRepository.update(Book(id, title, author, released_date, stock),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _book.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _book.postValue(true)
            }
        )
    }

    suspend fun delete(id: String) {
        _isLoading.postValue(true)
        bookRepository.delete(id,
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _book.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _book.postValue(true)
            }
        )
    }

    suspend fun find(id: String) {
        val book = bookRepository.find(id)
        book?.let {
            _item.postValue(it)
        }
    }
}