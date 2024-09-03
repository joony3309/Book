package com.baripark.book.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://openapi.naver.com"

    private const val NAVER_CLIENT_ID = "2g27WX5y_o5n2lPIAj27"
    private const val NAVER_CLIENT_SECRET = "wfR8bPspa_"

    val bookService: BookService by lazy {
        retrofit.create(BookService::class.java)
    }


    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader("X-Naver-Client-Id", NAVER_CLIENT_ID)
                    .addHeader("X-Naver-Client-Secret", NAVER_CLIENT_SECRET)
                    .build()
            )
        }
        .build()

    private val gsonConverterFactory = GsonConverterFactory.create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()
}