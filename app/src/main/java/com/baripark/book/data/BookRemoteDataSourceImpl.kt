package com.baripark.book.data

import com.baripark.book.api.BookService
import com.baripark.book.model.Book

class BookRemoteDataSourceImpl(
    private val bookService: BookService
): BookRemoteDataSource {
    override suspend fun getBooks(query: String): List<Book> = bookService.getBooks(query).items
}