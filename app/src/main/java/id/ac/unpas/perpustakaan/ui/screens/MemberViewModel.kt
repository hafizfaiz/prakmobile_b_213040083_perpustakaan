package id.ac.unpas.perpustakaan.ui.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unpas.perpustakaan.base.LiveCoroutinesViewModel
import id.ac.unpas.perpustakaan.models.Membership
import id.ac.unpas.perpustakaan.repositories.MembershipRepository
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(private val membershipRepository: MembershipRepository) : LiveCoroutinesViewModel() {

    private val _isDone: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDone: LiveData<Boolean> get() = _isDone

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _item: MutableLiveData<Membership> = MutableLiveData()
    val item: LiveData<Membership> get() = _item

    private val _membership: MutableLiveData<Boolean> = MutableLiveData(false)
    val memberships: LiveData<List<Membership>> get() = _membership.switchMap {
        _isLoading.postValue(true)
        launchOnViewModelScope {
            membershipRepository.loadItems(
                onSuccess = {
                    _isLoading.postValue(false)
                },
                onError = {
                    _isLoading.postValue(false)
                    Log.e("MemberViewModel", it)
                }
            ).asLiveData()
        }
    }

    suspend fun insert(
        id: String,
        name: String,
        address: String,
        phone: String
    ) {
        _isLoading.postValue(true)
        membershipRepository.insert(
            Membership(id, name, address, phone),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _membership.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _membership.postValue(true)
            }
        )
    }

    suspend fun update(
        id: String,
        name: String,
        address: String,
        phone: String
    ) {
        _isLoading.postValue(true)
        membershipRepository.update(
            Membership(id, name, address, phone),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _membership.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _membership.postValue(true)
            }
        )
    }

    suspend fun delete(id: String) {
        _isLoading.postValue(true)
        membershipRepository.delete(id,
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _membership.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _membership.postValue(true)
            }
        )
    }

    private val _id = MutableLiveData<String>()

    init {
        _id.switchMap { id ->
            liveData {
                val membership = membershipRepository.find(id)
                emitSource(membership)
            }
        }.observeForever {
            _item.postValue(it)
        }
    }

    fun find(id: String) {
        _id.postValue(id)
    }
}