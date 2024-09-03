package com.baripark.book.data

import com.baripark.book.model.Book

interface BookRepository {
    suspend fun getBooks(query: String): List<Book>
    fun getLocalBooks(): List<Book>
    fun deleteBooks()
}