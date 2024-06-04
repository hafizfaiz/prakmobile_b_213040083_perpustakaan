package id.ac.unpas.perpustakaan.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.ac.unpas.perpustakaan.persistences.AppDatabase
import id.ac.unpas.perpustakaan.persistences.BookDao
import id.ac.unpas.perpustakaan.persistences.BookRequestDao
import id.ac.unpas.perpustakaan.persistences.MembershipDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application) : AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideBookDao(appDatabase: AppDatabase) : BookDao {
        return appDatabase.bookDao()
    }

    @Provides
    @Singleton
    fun provideMembershipDao(appDatabase: AppDatabase): MembershipDao {
        return appDatabase.membershipDao()
    }

    @Provides
    @Singleton
    fun provideBookRequestDao(appDatabase: AppDatabase): BookRequestDao {
        return appDatabase.bookRequestDao()
    }
}