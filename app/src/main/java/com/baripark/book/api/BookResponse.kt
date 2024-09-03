package com.baripark.book.api

import com.baripark.book.model.Book

data class BookResponse(
    val title: String,
    val link: String,
    val description: String,
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<Book>
)