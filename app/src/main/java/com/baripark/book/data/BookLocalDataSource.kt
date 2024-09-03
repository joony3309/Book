package com.baripark.book.data

import com.baripark.book.model.Book

interface BookLocalDataSource {
    fun getLocalBooks(): List<Book>
    fun insertBooks(books: List<Book>)
}