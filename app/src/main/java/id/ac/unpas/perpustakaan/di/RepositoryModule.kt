package id.ac.unpas.perpustakaan.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.ac.unpas.perpustakaan.networks.BookApi
import id.ac.unpas.perpustakaan.repositories.BookRepository
import id.ac.unpas.perpustakaan.persistences.BookDao

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideBookRepository(bookDao: BookDao, bookApi: BookApi): BookRepository {
        return BookRepository(bookApi, bookDao)
    }
}