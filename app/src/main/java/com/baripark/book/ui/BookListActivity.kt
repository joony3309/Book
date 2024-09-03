package com.baripark.book.ui

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.baripark.book.api.ApiClient
import com.baripark.book.data.BookLocalDataSource
import com.baripark.book.data.BookLocalDataSourceImpl
import com.baripark.book.data.BookRemoteDataSource
import com.baripark.book.data.BookRemoteDataSourceImpl
import com.baripark.book.data.BookRepository
import com.baripark.book.data.BookRepositoryImpl
import com.baripark.book.databinding.ActivityMainBinding
import com.baripark.book.model.Book
import com.baripark.book.room.AppDataBase
import kotlinx.coroutines.launch

class BookListActivity : AppCompatActivity(), BookListContract.View {

    private lateinit var binding: ActivityMainBinding

    private val presenter: BookListContract.Presenter by lazy {
        BookListPresenter(this, bookRepository)
    }

    private val bookRepository: BookRepository by lazy {
        val bookRemoteDataSource: BookRemoteDataSource =
            BookRemoteDataSourceImpl(ApiClient.bookService)
        val bookLocalDataSource: BookLocalDataSource =
            BookLocalDataSourceImpl(AppDataBase.getInstance(this).bookDao())
        BookRepositoryImpl(bookRemoteDataSource, bookLocalDataSource)
    }

    private val bookListAdapter by lazy {
        BookListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.also {
            it.adapter = bookListAdapter
            it.layoutManager = LinearLayoutManager(this)
        }
        binding.textInputEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                getBooks()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        binding.searchButton.setOnClickListener {
            getBooks()
        }

        presenter.getLocalBooks()
    }

    private fun getBooks() {
        val query = binding.textInputEditText.text.toString()

        lifecycleScope.launch {
            presenter.getBooks(query)
        }
    }

    override fun setBookItems(books: List<Book>) {
        bookListAdapter.submitList(books)
    }
}