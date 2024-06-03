package id.ac.unpas.perpustakaan.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.perpustakaan.models.Book
import id.ac.unpas.perpustakaan.models.Membership


@Database(entities = [Book::class,Membership::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun membershipDao(): MembershipDao
    companion object {
        const val DATABASE_NAME = "perpustakaan-database"
    }
}
