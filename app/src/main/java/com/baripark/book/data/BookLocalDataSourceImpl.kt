package com.baripark.book.data

import com.baripark.book.model.Book
import com.baripark.book.room.BookDao

class BookLocalDataSourceImpl(
    private val bookDao: BookDao
) : BookLocalDataSource {
    override fun getLocalBooks(): List<Book> = bookDao.getBooks()
    override fun insertBooks(books: List<Book>) = bookDao.insertBookList(books)
    override fun deleteBooks() = bookDao.deleteAllBooks()
}