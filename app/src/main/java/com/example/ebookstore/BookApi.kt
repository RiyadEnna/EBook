package com.example.ebookstore

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class BookApi {
    interface BookService {
        @GET("volumes")
        fun getBooks(@Query("q") criteria: String): Call<BookApiResponce>
    }
    //https://www.googleapis.com/books/v1/volumes?q=titreDuLivre
    private val baseUrl = "https://www.googleapis.com/books/v1/"

    fun getClient(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}