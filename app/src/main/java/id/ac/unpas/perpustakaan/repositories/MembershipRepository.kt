package id.ac.unpas.perpustakaan.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import id.ac.unpas.perpustakaan.models.Membership
import id.ac.unpas.perpustakaan.networks.MembershipApi
import id.ac.unpas.perpustakaan.persistences.MembershipDao
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MembershipRepository @Inject constructor(private val api: MembershipApi, private val dao: MembershipDao) {

    @WorkerThread
    fun loadItems(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        val list: List<Membership> = dao.findAll()
        api.findAll()
            .suspendOnSuccess {
                data.whatIfNotNull { response ->
                    dao.upsert(response.data)
                    val localList = dao.findAll()
                    emit(localList)
                    onSuccess()
                }
            }
            .suspendOnError {
                emit(list)
                onError(message())
            }
            .suspendOnException {
                emit(list)
                onError(message())
            }
    }



    suspend fun insert(
        membership: Membership,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        dao.upsert(membership)
        api.insert(membership)
            .suspendOnSuccess {
                data.whatIfNotNull {
                    if (it.success) {
                        onSuccess()
                    } else {
                        onError(it.message)
                    }
                }
            }
            .suspendOnError {
                onError(message())
            }
            .suspendOnException {
                onError(message())
            }
    }

    suspend fun update(
        membership: Membership,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        dao.upsert(membership)
        api.update(membership.id, membership)
            .suspendOnSuccess {
                data.whatIfNotNull {
                    if (it.success) {
                        onSuccess()
                    } else {
                        onError(it.message)
                    }
                }
            }
            .suspendOnError {
                onError(message())
            }
            .suspendOnException {
                onError(message())
            }
    }

    suspend fun delete(
        id: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        dao.delete(id)
        api.delete(id)
            .suspendOnSuccess {
                data.whatIfNotNull {
                    if (it.success) {
                        onSuccess()
                    } else {
                        onError(it.message)
                    }
                }
            }
            .suspendOnError {
                onError(message())
            }
            .suspendOnException {
                onError(message())
            }
    }

    fun find(id: String): LiveData<Membership> {
        return dao.find(id)
    }
}
