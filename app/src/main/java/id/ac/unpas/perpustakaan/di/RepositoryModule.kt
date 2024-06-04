package id.ac.unpas.perpustakaan.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.ac.unpas.perpustakaan.networks.BookApi
import id.ac.unpas.perpustakaan.networks.BookRequestApi
import id.ac.unpas.perpustakaan.networks.MembershipApi
import id.ac.unpas.perpustakaan.repositories.BookRepository
import id.ac.unpas.perpustakaan.repositories.BookRequestRepository
import id.ac.unpas.perpustakaan.repositories.MembershipRepository
import id.ac.unpas.perpustakaan.persistences.BookDao
import id.ac.unpas.perpustakaan.persistences.BookRequestDao
import id.ac.unpas.perpustakaan.persistences.MembershipDao

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideBookRepository(bookDao: BookDao, bookApi: BookApi): BookRepository {
        return BookRepository(bookApi, bookDao)
    }
    @Provides
    @ViewModelScoped
    fun provideMembershipRepository(membershipDao: MembershipDao, membershipApi: MembershipApi): MembershipRepository {
        return MembershipRepository(membershipApi, membershipDao)
    }
//    @Provides
//    @ViewModelScoped
//    fun provideBookRequestRepository(bookRequestDao: BookRequestDao, bookRequestApi: BookRequestApi): BookRequestRepository {
//        return BookRequestRepository(BookRequestApi, BookRequestDao)
//    }
}