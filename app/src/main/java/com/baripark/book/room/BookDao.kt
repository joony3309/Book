package com.baripark.book.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.baripark.book.model.Book

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookList(books: List<Book>)

    @Query("SELECT * FROM Book")
    fun getBooks(): List<Book>

    @Query("DELETE FROM Book")
    fun deleteAllBooks()
}