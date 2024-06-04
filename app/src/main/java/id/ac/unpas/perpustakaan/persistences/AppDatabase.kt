package id.ac.unpas.perpustakaan.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import id.ac.unpas.perpustakaan.models.Book
import id.ac.unpas.perpustakaan.models.Membership
import id.ac.unpas.perpustakaan.models.BookRequest

@Database(entities = [Book::class, Membership::class, BookRequest::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun membershipDao(): MembershipDao
    abstract fun bookRequestDao(): BookRequestDao

    companion object {
        const val DATABASE_NAME = "perpustakaan-database"

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Implementasi migrasi dari versi 1 ke versi 2
                // Untuk contoh, saya akan menambahkan kolom baru ke tabel Book
                database.execSQL("ALTER TABLE Book ADD COLUMN new_column_name TEXT")
            }
        }
    }
}
