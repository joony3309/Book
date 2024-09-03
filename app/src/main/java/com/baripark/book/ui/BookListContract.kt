package com.baripark.book.ui

import com.baripark.book.model.Book

interface BookListContract {
    interface View {
        fun setBookItems(books: List<Book>)
    }
    interface Presenter {
        fun getLocalBooks()
        suspend fun getBooks(query: String)
    }
}