package com.baripark.book.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.baripark.book.databinding.ActivityDetailBookBinding
import com.baripark.book.model.Book
import com.bumptech.glide.Glide
import java.util.Locale

class BookDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding = ActivityDetailBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val book = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("book", Book::class.java)
        } else {
            intent.getParcelableExtra<Book>("book")
        }

        with(binding) {
            book?.let {
                titleTextView.text = it.title
                authorTextView.text = it.author
                discountTextView.text = String.format(Locale.KOREA, "%,d 원", it.discount.toInt())
                descriptionTextView.text = it.description
                publisherTextView.text = it.publisher
                pubDateTextView.text = it.pubdate
                imgUrlButton.setOnClickListener { _ ->
                    val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it.link))
                    startActivity(urlIntent)
                }

                Glide.with(imageView)
                    .load(it.image)
                    .centerCrop()
                    .into(imageView)
            }
        }
    }
}