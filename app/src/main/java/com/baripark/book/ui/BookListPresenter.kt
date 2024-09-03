package com.baripark.book.ui

import com.baripark.book.data.BookRepository

class BookListPresenter(
    private val view: BookListContract.View,
    private val bookRepository: BookRepository
): BookListContract.Presenter {
    override suspend fun getLocalBooks() {
        val books = bookRepository.getLocalBooks()
        if (books.isNotEmpty()) {
            view.setBookItems(books)
        }
    }

    override suspend fun getBooks(query: String) {
        if (query.isEmpty()) return

        view.showLoading()
        view.hideKeyboard()
        val books = bookRepository.getBooks(query)
        view.hideLoading()
        if (books.isNotEmpty()) {
            view.setBookItems(books)
        }
    }

    override suspend fun deleteBooks() {
        view.hideKeyboard()
        bookRepository.deleteBooks()
        view.clearBookItems()
    }
}