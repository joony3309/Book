package com.baripark.book.model

import androidx.room.Entity

@Entity(primaryKeys = [("title")])
data class Book(
    val title: String,
    val link: String,
    val image: String,
    val author: String,
    val price: Long,
    val discount: Long,
    val publisher: String,
    val pubdate: String,
    val isbn: String,
    val description: String,
)
