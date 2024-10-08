package com.baripark.book.api

import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET("/v1/search/book.json")
    suspend fun getBooks(
        @Query("query") query: String
    ): BookResponse
//
//    @GET("/v1/search/book_adv.json")
//    suspend fun getBook(
//        @Query("d_isbn") isbn: String
//    ): BookResponse
}