package id.ac.unpas.perpustakaan.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.perpustakaan.models.Book


@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        const val DATABASE_NAME = "perpustakaan-database"
    }
}