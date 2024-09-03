package com.baripark.book.ui

import com.baripark.book.model.Book

interface BookListContract {
    interface View {
        fun setBookItems(books: List<Book>)
        fun clearBookItems()
        fun showLoading()
        fun hideLoading()
        fun hideKeyboard()
    }
    interface Presenter {
        suspend fun getLocalBooks()
        suspend fun getBooks(query: String)
        suspend fun deleteBooks()
    }
}