package com.baripark.book.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.baripark.book.model.Book

@Database(
    entities = [Book::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase: RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            if (INSTANCE == null) {
                synchronized(AppDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java,
                        "Book.db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }

            return INSTANCE!!
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}