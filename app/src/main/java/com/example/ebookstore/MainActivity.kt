package com.example.ebookstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ebookstore.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object {
        val bookKey = "bookKey"
        val imageKey = "imageKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewItems()
    }

    private fun setViewItems() {
        val storedSearch = SharedPreferencesManager().getSearchCriteria(this)
        if (storedSearch != null) {
            binding.searchEt.setText(storedSearch)
        }

        binding.searchBt.setOnClickListener {
            checkUserInput()
            callService()
            binding.searchBt.visibility = View.INVISIBLE
            binding.progress.visibility = View.VISIBLE
        }

        binding.favoritesBt.setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }
    }

    private fun checkUserInput() {
        if (binding.searchEt.text.toString().isEmpty()) {
            Toast.makeText(this, "Veuillez effectuer une saisie", Toast.LENGTH_LONG).show()
        }
    }

    private fun callService() {
        val service: BookApi.BookService = BookApi().getClient().create(BookApi.BookService::class.java)
        val call: Call<BookApiResponce> = service.getBooks(binding.searchEt.text.toString())
        call.enqueue(object : Callback<BookApiResponce> {
            override fun onResponse(call: Call<BookApiResponce>, response: Response<BookApiResponce>) {
                processResponse(response)
                searchEnded()
            }

            override fun onFailure(call: Call<BookApiResponce>, t: Throwable) {
                processFailure(t)
                searchEnded()
            }
        })
    }

    private fun searchEnded() {
        binding.searchBt.visibility = View.VISIBLE
        binding.progress.visibility = View.INVISIBLE
        SharedPreferencesManager().saveSearchCriteria(binding.searchEt.text.toString(), this)
    }

    private fun processFailure(t: Throwable) {
        Toast.makeText(this, "Erreur", Toast.LENGTH_LONG).show()
        t.message?.let { Log.d("Erreur", it) }
    }

    private fun processResponse(response: Response<BookApiResponce>) {
        if (response.isSuccessful) {
            val body = response.body()
            if (body?.items!!.isNotEmpty()) {
                val adapter = BookListViewAdapter(body.items, object : BookItemCallback {
                    override fun onCellClick(data: Items) {
                        gotoNextActivity(data)
                    }

                    override fun onSaveBook(book: Items) {
                        saveBook(book)
                    }
                })
                val recyclerView = findViewById<RecyclerView>(R.id.book_rv)
                binding.bookRv.adapter = adapter
                binding.bookRv.layoutManager = LinearLayoutManager(applicationContext)
            }
        } else {
            Toast.makeText(this, "Erreur lors de la récupération des données", Toast.LENGTH_LONG).show()
        }
    }

    private fun saveBook(book: Items) {
        if (SharedPreferencesManager().saveBook(book, this)){
            Toast.makeText(this,"Enregistrement bien effectué", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this,"Ce livre est déjà dans vos favoris", Toast.LENGTH_LONG).show()
        }
    }


    private fun gotoNextActivity(data: Items) {
        val intent = Intent(this, BookDetailActivity::class.java)
        intent.putExtra(bookKey, data.volumeInfo?.title)
        intent.putExtra(imageKey, data.volumeInfo?.imageLinks?.thumbnail)
        startActivity(intent)
    }

}