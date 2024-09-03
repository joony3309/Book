package com.baripark.book.data

import com.baripark.book.model.Book

interface BookRemoteDataSource {
    suspend fun getBooks(query: String): List<Book>
}