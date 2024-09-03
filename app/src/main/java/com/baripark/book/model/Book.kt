package com.baripark.book.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Entity(primaryKeys = [("title")])
@Parcelize
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
) : Parcelable
