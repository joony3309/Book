package com.baripark.book.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.baripark.book.databinding.ItemBookBinding
import com.baripark.book.model.Book

class BookListAdapter : ListAdapter<Book, BookViewHolder>(diffUtils) {


    companion object {
        val diffUtils = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem.isbn == newItem.isbn

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder =
        BookViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) =
        holder.bind(getItem(position))
}