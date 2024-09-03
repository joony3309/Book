package com.baripark.book.data

import com.baripark.book.model.Book

class BookRepositoryImpl(
    private val bookRemoteDataSource: BookRemoteDataSource,
    private val bookLocalDataSource: BookLocalDataSource
) : BookRepository {
    override suspend fun getBooks(query: String): List<Book> {
        val books = bookRemoteDataSource.getBooks(query)
        bookLocalDataSource.insertBooks(books)

        return books
    }

    override fun getLocalBooks(): List<Book> {
        return bookLocalDataSource.getLocalBooks()
    }

    override fun deleteBooks() {
        bookLocalDataSource.deleteBooks()
    }
}