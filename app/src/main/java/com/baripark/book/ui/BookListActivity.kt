package com.baripark.book.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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
        BookListPresenter(view = this, bookRepository = bookRepository)
    }

    private val bookRepository: BookRepository by lazy {
        val bookRemoteDataSource: BookRemoteDataSource =
            BookRemoteDataSourceImpl(ApiClient.bookService)
        val bookLocalDataSource: BookLocalDataSource =
            BookLocalDataSourceImpl(AppDataBase.getInstance(context = this).bookDao())

        BookRepositoryImpl(
            bookRemoteDataSource = bookRemoteDataSource,
            bookLocalDataSource = bookLocalDataSource
        )
    }

    private val bookListAdapter by lazy {
        BookListAdapter {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.link))
            startActivity(intent)
        }
    }

    private val imm: InputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        getLocalBooks()
    }

    private fun initViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.also {
            it.adapter = bookListAdapter
            it.layoutManager = LinearLayoutManager(this)
        }
        binding.textInputEditText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                getBooks(v.text.toString())
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        binding.searchButton.setOnClickListener {
            getBooks(binding.textInputEditText.text.toString())
        }
        binding.fab.setOnClickListener {
            deleteBooks()
        }
    }

    private fun getBooks(query: String) {
        lifecycleScope.launch {
            presenter.getBooks(query)
        }
    }

    private fun getLocalBooks() {
        lifecycleScope.launch {
            presenter.getLocalBooks()
        }
    }

    private fun deleteBooks() {
        lifecycleScope.launch {
            presenter.deleteBooks()
        }
    }

    override fun setBookItems(books: List<Book>) {
        bookListAdapter.submitList(books)
    }

    override fun clearBookItems() {
        bookListAdapter.submitList(null)
        binding.textInputEditText.text?.clear()
    }

    override fun showLoading() {
        binding.progressBar.isVisible = true
    }

    override fun hideLoading() {
        binding.progressBar.isVisible = false
    }

    override fun hideKeyboard() {
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}