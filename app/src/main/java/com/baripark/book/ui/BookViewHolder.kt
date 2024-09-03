package com.baripark.book.ui

import androidx.recyclerview.widget.RecyclerView
import com.baripark.book.databinding.ItemBookBinding
import com.baripark.book.model.Book
import com.bumptech.glide.Glide
import java.util.Locale

class BookViewHolder(
    private val binding: ItemBookBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Book) {
        with(binding) {
            Glide.with(image)
                .load(item.image)
                .centerCrop()
                .into(image)

            title.text = item.title
            author.text = item.author
            price.text = String.format(Locale.KOREA, "%,d 원", item.price.toInt())
            discount.text = String.format(Locale.KOREA, "%,d 원", item.discount.toInt())
        }
    }
}